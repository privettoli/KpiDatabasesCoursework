package ua.kpi.databases.coursework

import com.github.andrewoma.kwery.core.DefaultSession
import com.github.andrewoma.kwery.core.Row
import com.github.andrewoma.kwery.core.dialect.MysqlDialect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ua.kpi.databases.coursework.DBConnection.session
import ua.kpi.databases.coursework.DBConnection.logSQL
import java.nio.file.Files
import java.nio.file.Paths
import java.sql.DriverManager
import java.sql.DriverManager.getConnection
import java.util.*
import kotlin.collections.firstOrNull
import kotlin.collections.mapOf
import kotlin.text.replace
import kotlin.text.replaceFirst

/**
 * Created by privettoli on 11.01.2016.
 */
object DBConnection {
    val logger = LoggerFactory.getLogger(DBConnection.javaClass)
    val databaseSettings = Properties().apply {
        logger.debug("Initializing properties file")
        val loader = DBConnection.javaClass.classLoader;
        loader.getResourceAsStream("db/settings.properties")?.use { this.load(it) }
                ?: throw IllegalStateException("Can't find database settings properties file")
        logger.debug("Properties file initialized")
    }
    val session: DefaultSession
        get() = {
            val url: String? = databaseSettings.getProperty("url")
            val username: String? = databaseSettings.getProperty("username")
            val password: String? = databaseSettings.getProperty("password")
            val connectionParameters = "Url: $url, username: $username, password: $password"
            if (url == null || username == null || password == null) {
                throw IllegalStateException("Null value! $connectionParameters");
            }
            logger.debug("Connecting. $connectionParameters")
            val connection = getConnection(url, username, password)
            DefaultSession(connection, MysqlDialect())
        }()
    var sqlLogCallback: (String) -> Unit = {}
    fun logSQL(sql: String) = sqlLogCallback(sql)
}

interface DAO<T> {
    fun findAll(): List<T>
    fun update(t: T)
    fun findBy(id: Int): T?
    fun remove(t: T)
}

object PlantDAO : DAO<PlantEntity> {
    override fun remove(t: PlantEntity) {
        val sql = "DELETE FROM `plants` WHERE `id` = :id ;"
        logSQL(sql.replaceFirst(":id", t.id.toString()))
        session.update(sql, mapOf("id" to t.id))
    }

    override fun update(t: PlantEntity) {
        val sql = "UPDATE `plants` " +
                "SET `id` = :id, " +
                "`name` = :name," +
                "`area_ares = :area`;"
        logSQL(sql.replaceFirst(":id", t.id.toString())
                .replaceFirst(":name", t.name)
                .replaceFirst(":area", t.area.toString()))
        session.update(sql, mapOf("id" to t.id,
                "name" to t.name, "area" to t.area))
    }

    override fun findBy(id: Int): PlantEntity? {
        val sql = "SELECT * FROM `plants` WHERE `id` = :id;"
        logSQL(sql.replaceFirst(":id", id.toString()))
        val plant = session.select(sql, mapOf("id" to id)) { parseRow(it) }
        return plant.firstOrNull()
    }

    override fun findAll(): List<PlantEntity> {
        val sql = "SELECT * FROM `plants`;"
        logSQL(sql)
        val plants = session.select(sql) { parseRow(it) }
        return plants
    }

    private fun parseRow(it: Row) = PlantEntity(it.int("id"), it.string("name"), it.float("area_ares"))
}