package ua.kpi.databases.coursework.dao

import com.github.andrewoma.kwery.core.Row
import ua.kpi.databases.coursework.PenaltyReceipt
import ua.kpi.databases.coursework.dao.DBConnection.logSQL
import ua.kpi.databases.coursework.dao.DBConnection.session
import ua.kpi.databases.coursework.replace
import ua.kpi.databases.coursework.secure
import ua.kpi.databases.coursework.with
import kotlin.collections.emptyList
import kotlin.collections.firstOrNull
import kotlin.collections.mapOf

object PenaltyReceiptDAO : DAO<PenaltyReceipt> {
    override fun prefix(): String {
        return "penalty.receipt."
    }

    override fun insert(t: PenaltyReceipt) {
        val sql = "INSERT INTO `penalty_receipts` (`type_id`, " +
                "`tax`, `penalty_receipt_sum`) VALUES (:typeId, :tax, " +
                ":sum);"
        logSQL(merge(sql, t))
        secure { session.insert(sql, mapper(t)) { } }
    }

    private fun mapper(t: PenaltyReceipt): Map<String, Any?> {
        return mapOf("id" to t.id,
                "typeId" to t.typeId,
                "tax" to t.tax,
                "sum" to t.sum)
    }

    override fun remove(t: PenaltyReceipt) {
        val sql = "DELETE FROM `penalty_receipts` WHERE `id` = :id ;"
        logSQL(sql.replace(":id" with t.id.toString()))
        secure { session.update(sql, mapOf("id" to t.id)) }
    }

    override fun update(t: PenaltyReceipt) {
        val sql = "UPDATE `penalty_receipts` SET " +
                "`type_id` = :typeId, " +
                "`tax` = :tax, " +
                "`penalty_receipt_sum` = :sum " +
                "WHERE `id` = :id;"
        logSQL(merge(sql, t))
        secure { session.update(sql, mapper(t)) }
    }

    override fun findBy(id: Int): PenaltyReceipt? {
        val sql = "SELECT * FROM `penalty_receipts` WHERE `id` = :id;"
        logSQL(sql.replace(":id" with id))
        val penalty_receipt = secure { session.select(sql, mapOf("id" to id)) { parseRow(it) } }
        return penalty_receipt?.firstOrNull()
    }

    override fun findAll(): List<PenaltyReceipt> {
        val sql = "SELECT * FROM `penalty_receipts`;"
        logSQL(sql)
        val receipts = secure { session.select(sql) { parseRow(it) } }
        return receipts ?: emptyList()
    }

    private fun merge(sql: String, t: PenaltyReceipt): String {
        return sql.replace(":id" with t.id)
                .replace(":typeId" with t.typeId)
                .replace(":tax" with t.tax)
                .replace(":sum" with t.sum)

    }

    private fun parseRow(it: Row):PenaltyReceipt{
        return PenaltyReceipt(it.int("id"),
                it.int("type_id"),
                it.float("tax"),
                it.float("penalty_receipt_sum")
        )
    }
}