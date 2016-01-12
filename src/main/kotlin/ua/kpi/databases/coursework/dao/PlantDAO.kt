package ua.kpi.databases.coursework.dao

import com.github.andrewoma.kwery.core.Row
import ua.kpi.databases.coursework.Plant
import ua.kpi.databases.coursework.dao.DBConnection.logSQL
import ua.kpi.databases.coursework.dao.DBConnection.session
import ua.kpi.databases.coursework.replace
import ua.kpi.databases.coursework.secure
import ua.kpi.databases.coursework.with
import kotlin.collections.emptyList
import kotlin.collections.firstOrNull
import kotlin.collections.mapOf

object PlantDAO : DAO<Plant> {
    override fun prefix(): String {
        return "plant."
    }

    override fun insert(t: Plant) {
        val sql = "INSERT INTO `plants` (`name`, `area_ares`) VALUES (:name, :area);"
        logSQL(merge(sql, t))
        secure { session.insert(sql, mapper(t)) { } }
    }

    private fun mapper(t: Plant): Map<String, Any?> {
        return mapOf("id" to t.id, "name" to t.name, "area" to t.area)
    }

    override fun remove(t: Plant) {
        val sql = "DELETE FROM `plants` WHERE `id` = :id ;"
        logSQL(sql.replace(":id" with t.id.toString()))
        secure { session.update(sql, mapOf("id" to t.id)) }
    }

    override fun update(t: Plant) {
        val sql = "UPDATE `plants` SET " +
                "`name` = :name," +
                "`area_ares` = :area " +
                "WHERE `id` = :id;"
        logSQL(merge(sql, t))
        secure { session.update(sql, mapper(t)) }
    }

    override fun findBy(id: Int): Plant? {
        val sql = "SELECT * FROM `plants` WHERE `id` = :id;"
        logSQL(sql.replace(":id" with id))
        val plant = secure { session.select(sql, mapOf("id" to id)) { parseRow(it) } }
        return plant?.firstOrNull()
    }

    override fun findAll(): List<Plant> {
        val sql = "SELECT * FROM `plants`;"
        logSQL(sql)
        val plants = secure { session.select(sql) { parseRow(it) } }
        return plants ?: emptyList()
    }

    private fun merge(sql: String, t: Plant): String {
        return sql.replace(":id" with t.id)
                .replace(":name" with t.name)
                .replace(":area" with t.area)
    }

    private fun parseRow(it: Row) = Plant(it.int("id"), it.string("name"), it.float("area_ares"))
}