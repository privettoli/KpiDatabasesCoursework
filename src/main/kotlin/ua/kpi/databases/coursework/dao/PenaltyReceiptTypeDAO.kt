package ua.kpi.databases.coursework.dao

import com.github.andrewoma.kwery.core.Row
import ua.kpi.databases.coursework.PenaltyReceiptType
import ua.kpi.databases.coursework.dao.DBConnection.logSQL
import ua.kpi.databases.coursework.dao.DBConnection.session
import ua.kpi.databases.coursework.replace
import ua.kpi.databases.coursework.secure
import ua.kpi.databases.coursework.with
import kotlin.collections.emptyList
import kotlin.collections.firstOrNull
import kotlin.collections.mapOf

object PenaltyReceiptTypeDAO : DAO<PenaltyReceiptType> {
    override fun prefix(): String {
        return "penalty.receipt.type."
    }

    override fun insert(t: PenaltyReceiptType) {
        val sql = "INSERT INTO `penalty_receipt_types` (`name`) VALUES (:name);"
        logSQL(merge(sql, t))
        secure { session.insert(sql, mapper(t)) { } }
    }

    private fun mapper(t: PenaltyReceiptType): Map<String, Any?> {
        return mapOf("id" to t.id,
                "name" to t.name)
    }

    override fun remove(t: PenaltyReceiptType) {
        val sql = "DELETE FROM `penalty_receipt_types` WHERE `id` = :id ;"
        logSQL(sql.replace(":id" with t.id.toString()))
        secure { session.update(sql, mapOf("id" to t.id)) }
    }

    override fun update(t: PenaltyReceiptType) {
        val sql = "UPDATE `penalty_receipt_types` SET `name` = :name WHERE `id` = :id;"
        logSQL(merge(sql, t))
        secure { session.update(sql, mapper(t)) }
    }

    override fun findBy(id: Int): PenaltyReceiptType? {
        val sql = "SELECT * FROM `penalty_receipt_types` WHERE `id` = :id;"
        logSQL(sql.replace(":id" with id))
        val penalty_receipt_type = secure { session.select(sql, mapOf("id" to id)) { parseRow(it) } }
        return penalty_receipt_type?.firstOrNull()
    }

    override fun findAll(): List<PenaltyReceiptType> {
        val sql = "SELECT * FROM `penalty_receipt_types`;"
        logSQL(sql)
        val requests = secure { session.select(sql) { parseRow(it) } }
        return requests ?: emptyList()
    }

    private fun merge(sql: String, t: PenaltyReceiptType): String {
        return sql.replace(":id" with t.id)
                .replace(":name" with t.name)
    }

    private fun parseRow(it: Row):PenaltyReceiptType{
        return PenaltyReceiptType(it.int("id"),
                it.string("name")
        )
    }
}