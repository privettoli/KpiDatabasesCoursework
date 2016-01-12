package ua.kpi.databases.coursework.dao

import com.github.andrewoma.kwery.core.DefaultSession
import com.github.andrewoma.kwery.core.dialect.MysqlDialect
import org.slf4j.LoggerFactory
import java.sql.DriverManager.getConnection
import java.util.*

object DBConnection {
    val logger = LoggerFactory.getLogger(javaClass)
    val databaseSettings = Properties().apply {
        logger.debug("Initializing properties file")
        val loader: ClassLoader = DBConnection.javaClass.classLoader
                ?: throw IllegalStateException("ClassLoader is ${javaClass.classLoader}");
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