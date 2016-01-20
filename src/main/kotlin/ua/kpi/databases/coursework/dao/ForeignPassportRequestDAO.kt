package ua.kpi.databases.coursework.dao

import com.github.andrewoma.kwery.core.Row
import ua.kpi.databases.coursework.ForeignPassportRequest
import ua.kpi.databases.coursework.dao.DBConnection.logSQL
import ua.kpi.databases.coursework.dao.DBConnection.session
import ua.kpi.databases.coursework.replace
import ua.kpi.databases.coursework.secure
import ua.kpi.databases.coursework.with
import kotlin.collections.emptyList
import kotlin.collections.firstOrNull
import kotlin.collections.mapOf

object ForeignPassportRequestDAO : DAO<ForeignPassportRequest> {
    override fun prefix(): String {
        return "foreign.passport.request."
    }

    override fun insert(t: ForeignPassportRequest) {
        val sql = "INSERT INTO `foreign_passport_requests` (`national_passport_series`, " +
                "`national_passport_number`, `conviction_absence_certificate_number`, `military_certificate_number`) " +
                "VALUES (:nationalPassportSeries, :nationalPassportNumber, " +
                ":convictionAbsenceCertificateNumber, :militaryCertificateNumber);"
        logSQL(merge(sql, t))
        secure { session.insert(sql, mapper(t)) { } }
    }

    private fun mapper(t: ForeignPassportRequest): Map<String, Any?> {
        return mapOf("id" to t.id,
                "nationalPassportSeries" to t.nationalPassportSeries,
                "nationalPassportNumber" to t.nationalPassportNumber,
                "convictionAbsenceCertificateNumber" to t.convictionAbsenceCertificateNumber,
                "militaryCertificateNumber" to t.militaryCertificateNumber)
    }

    override fun remove(t: ForeignPassportRequest) {
        val sql = "DELETE FROM `foreign_passport_requests` WHERE `id` = :id ;"
        logSQL(sql.replace(":id" with t.id.toString()))
        secure { session.update(sql, mapOf("id" to t.id)) }
    }

    override fun update(t: ForeignPassportRequest) {
        val sql = "UPDATE `foreign_passport_requests` SET " +
                "`national_passport_series` = :nationalPassportSeries, " +
                "`national_passport_number` = :nationalPassportNumber, " +
                "`conviction_absence_certificate_number` = :convictionAbsenceCertificateNumber, " +
                "`military_certificate_number` = :militaryCertificateNumber "  +
                "WHERE `id` = :id;"
        logSQL(merge(sql, t))
        secure { session.update(sql, mapper(t)) }
    }

    override fun findBy(id: Int): ForeignPassportRequest? {
        val sql = "SELECT * FROM `foreign_passport_requests` WHERE `id` = :id;"
        logSQL(sql.replace(":id" with id))
        val foreign_passport_request = secure { session.select(sql, mapOf("id" to id)) { parseRow(it) } }
        return foreign_passport_request?.firstOrNull()
    }

    override fun findAll(): List<ForeignPassportRequest> {
        val sql = "SELECT * FROM `foreign_passport_requests`;"
        logSQL(sql)
        val requests = secure { session.select(sql) { parseRow(it) } }
        return requests ?: emptyList()
    }

    private fun merge(sql: String, t: ForeignPassportRequest): String {
        return sql.replace(":id" with t.id)
                .replace(":nationalPassportSeries" with t.nationalPassportSeries)
                .replace(":nationalPassportNumber" with t.nationalPassportNumber)
                .replace(":convictionAbsenceCertificateNumber" with t.convictionAbsenceCertificateNumber)
                .replace(":militaryCertificateNumber" with t.militaryCertificateNumber)
    }

    private fun parseRow(it: Row):ForeignPassportRequest{
        return ForeignPassportRequest(it.int("id"),
                it.string("national_passport_series"),
                it.int("national_passport_number"),
                it.string("conviction_absence_certificate_number"),
                it.string("military_certificate_number")
        )
    }
}