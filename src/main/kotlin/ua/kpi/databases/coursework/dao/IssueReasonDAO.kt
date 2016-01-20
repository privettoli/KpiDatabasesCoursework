package ua.kpi.databases.coursework.dao

import com.github.andrewoma.kwery.core.Row
import ua.kpi.databases.coursework.IssueReason
import ua.kpi.databases.coursework.dao.DBConnection.logSQL
import ua.kpi.databases.coursework.dao.DBConnection.session
import ua.kpi.databases.coursework.replace
import ua.kpi.databases.coursework.secure
import ua.kpi.databases.coursework.with
import kotlin.collections.emptyList
import kotlin.collections.firstOrNull
import kotlin.collections.mapOf

object IssueReasonDAO : DAO<IssueReason> {
    override fun prefix(): String {
        return "issue.reason."
    }

    override fun insert(t: IssueReason) {
        val sql = "INSERT INTO `issue_reasons` (`name`) VALUES (:name);"
        logSQL(merge(sql, t))
        secure { session.insert(sql, mapper(t)) { } }
    }

    private fun mapper(t: IssueReason): Map<String, Any?> {
        return mapOf("id" to t.id,
                "name" to t.name)
    }

    override fun remove(t: IssueReason) {
        val sql = "DELETE FROM `issue_reasons` WHERE `id` = :id ;"
        logSQL(sql.replace(":id" with t.id.toString()))
        secure { session.update(sql, mapOf("id" to t.id)) }
    }

    override fun update(t: IssueReason) {
        val sql = "UPDATE `issue_reasons` SET `name` = :name WHERE `id` = :id;"
        logSQL(merge(sql, t))
        secure { session.update(sql, mapper(t)) }
    }

    override fun findBy(id: Int): IssueReason? {
        val sql = "SELECT * FROM `issue_reasons` WHERE `id` = :id;"
        logSQL(sql.replace(":id" with id))
        val issue_reason = secure { session.select(sql, mapOf("id" to id)) { parseRow(it) } }
        return issue_reason?.firstOrNull()
    }

    override fun findAll(): List<IssueReason> {
        val sql = "SELECT * FROM `issue_reasons`;"
        logSQL(sql)
        val requests = secure { session.select(sql) { parseRow(it) } }
        return requests ?: emptyList()
    }

    private fun merge(sql: String, t: IssueReason): String {
        return sql.replace(":id" with t.id)
                .replace(":name" with t.name)
    }

    private fun parseRow(it: Row):IssueReason{
        return IssueReason(it.int("id"),
                it.string("name")
        )
    }
}