package ua.kpi.databases.coursework.dao

import com.github.andrewoma.kwery.core.Row
import ua.kpi.databases.coursework.BirthCertificate
import ua.kpi.databases.coursework.dao.DBConnection.logSQL
import ua.kpi.databases.coursework.dao.DBConnection.session
import ua.kpi.databases.coursework.replace
import ua.kpi.databases.coursework.secure
import ua.kpi.databases.coursework.with
import kotlin.collections.emptyList
import kotlin.collections.firstOrNull
import kotlin.collections.mapOf

object BirthCertificateDAO : DAO<BirthCertificate> {
    override fun prefix(): String {
        return "birth.certificate."
    }

    override fun insert(t: BirthCertificate) {
        val sql = "INSERT INTO `birth_certificates` (`birth_certificate_series`, " +
                "`birth_certificate_number`, `birth_date`) " +
                "VALUES (:series, :number, " +
                ":date);"
        logSQL(merge(sql, t))
        secure { session.insert(sql, mapper(t)) { } }
    }

    private fun mapper(t: BirthCertificate): Map<String, Any?> {
        return mapOf("id" to t.id,
                "series" to t.series,
                "number" to t.number,
                "date" to t.date)
    }

    override fun remove(t: BirthCertificate) {
        val sql = "DELETE FROM `birth_certificates` WHERE `id` = :id ;"
        logSQL(sql.replace(":id" with t.id.toString()))
        secure { session.update(sql, mapOf("id" to t.id)) }
    }

    override fun update(t: BirthCertificate) {
        val sql = "UPDATE `birth_certificates` SET " +
                "`birth_certificate_series` = :series, " +
                "`birth_certificate_number` = :number, " +
                "`birth_date` = :date " +
                "WHERE `id` = :id;"
        logSQL(merge(sql, t))
        secure { session.update(sql, mapper(t)) }
    }

    override fun findBy(id: Int): BirthCertificate? {
        val sql = "SELECT * FROM `birth_certificates` WHERE `id` = :id;"
        logSQL(sql.replace(":id" with id))
        val birth_certificate = secure { session.select(sql, mapOf("id" to id)) { parseRow(it) } }
        return birth_certificate?.firstOrNull()
    }

    override fun findAll(): List<BirthCertificate> {
        val sql = "SELECT * FROM `birth_certificates`;"
        logSQL(sql)
        val requests = secure { session.select(sql) { parseRow(it) } }
        return requests ?: emptyList()
    }

    private fun merge(sql: String, t: BirthCertificate): String {
        return sql.replace(":id" with t.id)
                .replace(":series" with t.series)
                .replace(":number" with t.number)
                .replace(":date" with t.date)
    }

    private fun parseRow(it: Row):BirthCertificate{
        return BirthCertificate(it.int("id"),
                it.string("birth_certificate_series"),
                it.int("birth_certificate_number"),
                it.string("birth_date")
        )
    }
}