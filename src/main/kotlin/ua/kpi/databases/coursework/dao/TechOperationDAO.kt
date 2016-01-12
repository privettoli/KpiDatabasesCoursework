package ua.kpi.databases.coursework.dao

import com.github.andrewoma.kwery.core.Row
import ua.kpi.databases.coursework.TechOperation
import ua.kpi.databases.coursework.replace
import ua.kpi.databases.coursework.secure
import ua.kpi.databases.coursework.with
import kotlin.collections.emptyList
import kotlin.collections.firstOrNull
import kotlin.collections.mapOf

object TechOperationDAO : DAO<TechOperation> {
    override fun prefix(): String {
        return "tech.operation."
    }

    override fun insert(t: TechOperation) {
        val sql = "INSERT INTO `tech_operations` " +
                "(`month`, `name`, `fuel_consumption_liters`, `processing_time_weeks`, `plant_id`) " +
                "VALUES (:month, :name, :fuelConsumptionLiters, :processingTimeWeeks, :plantId);"
        DBConnection.logSQL(merge(sql, t))
        secure { DBConnection.session.insert(sql, mapper(t)) { } }
    }

    override fun remove(t: TechOperation) {
        val sql = "DELETE FROM `tech_operations` WHERE `id` = :id ;"
        DBConnection.logSQL(sql.replace(":id" with t.id.toString()))
        secure { DBConnection.session.update(sql, mapOf("id" to t.id)) }
    }

    override fun update(t: TechOperation) {
        val sql = "UPDATE `tech_operations` SET " +
                "`month` = :month," +
                "`name` = :name," +
                "`fuel_consumption_liters` = :fuelConsumptionLiters, " +
                "`processing_time_weeks` = :processingTimeWeeks, " +
                "`plant_id` = :plantId " +
                "WHERE `id` = :id;"
        DBConnection.logSQL(merge(sql, t))
        secure { DBConnection.session.update(sql, mapper(t)) }
    }

    override fun findBy(id: Int): TechOperation? {
        val sql = "SELECT * FROM `tech_operations` WHERE `id` = :id;"
        DBConnection.logSQL(sql.replace(":id" with id))
        val plant = secure { DBConnection.session.select(sql, mapOf("id" to id)) { parseRow(it) } }
        return plant?.firstOrNull()
    }

    override fun findAll(): List<TechOperation> {
        val sql = "SELECT * FROM `tech_operations`;"
        DBConnection.logSQL(sql)
        val plants = secure { DBConnection.session.select(sql) { parseRow(it) } }
        return plants ?: emptyList()
    }

    private fun mapper(t: TechOperation) = mapOf("id" to t.id, "month" to t.month, "name" to t.name,
            "fuelConsumptionLiters" to t.fuelConsumptionLiters,
            "processingTimeWeeks" to t.processingTimeWeeks, "plantId" to t.plantId)

    private fun merge(sql: String, t: TechOperation): String {
        return sql.replace(":id" with t.id)
                .replace(":month" with t.month)
                .replace(":name" with t.name)
                .replace(":fuelConsumptionLiters" with t.fuelConsumptionLiters)
                .replace(":processingTimeWeeks" with t.processingTimeWeeks)
                .replace(":plantId" with t.plantId)
    }

    private fun parseRow(it: Row) = TechOperation(it.int("id"), it.int("month"), it.string("name"),
            it.float("fuel_consumption_liters"), it.float("processing_time_weeks"), it.int("plant_id"))
}