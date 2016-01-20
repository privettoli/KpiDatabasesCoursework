package ua.kpi.databases.coursework.dao

import com.github.andrewoma.kwery.core.Row
import ua.kpi.databases.coursework.UnregistrationRequest
import ua.kpi.databases.coursework.dao.DBConnection.logSQL
import ua.kpi.databases.coursework.dao.DBConnection.session
import ua.kpi.databases.coursework.replace
import ua.kpi.databases.coursework.secure
import ua.kpi.databases.coursework.with
import kotlin.collections.emptyList
import kotlin.collections.firstOrNull
import kotlin.collections.mapOf

object UnregistrationRequestDAO : DAO<UnregistrationRequest> {
    override fun prefix(): String {
        return "unregistration.request."
    }

    override fun insert(t: UnregistrationRequest) {
        val sql = "INSERT INTO `unregistration_requests` (`municipal_service_certificate_number`, " +
                "`national_passport_series`, `national_passport_number` )" +
                "VALUES (:municipalServiceCertificateNumber, :nationalPassportSeries, " +
                ":nationalPassportNumber);"
        logSQL(merge(sql, t))
        secure { session.insert(sql, mapper(t)) { } }
    }

    private fun mapper(t: UnregistrationRequest): Map<String, Any?> {
        return mapOf("id" to t.id,
                "municipalServiceCertificateNumber" to t.municipalServiceCertificateNumber,
                "nationalPassportSeries" to t.nationalPassportSeries,
                "nationalPassportNumber" to t.nationalPassportNumber)
    }

    override fun remove(t: UnregistrationRequest) {
        val sql = "DELETE FROM `unregistration_requests` WHERE `id` = :id ;"
        logSQL(sql.replace(":id" with t.id.toString()))
        secure { session.update(sql, mapOf("id" to t.id)) }
    }

    override fun update(t: UnregistrationRequest) {
        val sql = "UPDATE `unregistration_requests` SET " +
                "`municipal_service_certificate_number` = :municipalServiceCertificateNumber, " +
                "`national_passport_series` = :nationalPassportSeries, " +
                "`national_passport_number` = :nationalPassportNumber "  +
                "WHERE `id` = :id;"
        logSQL(merge(sql, t))
        secure { session.update(sql, mapper(t)) }
    }

    override fun findBy(id: Int): UnregistrationRequest? {
        val sql = "SELECT * FROM `unregistration_requests` WHERE `id` = :id;"
        logSQL(sql.replace(":id" with id))
        val unregistration_request = secure { session.select(sql, mapOf("id" to id)) { parseRow(it) } }
        return unregistration_request?.firstOrNull()
    }

    override fun findAll(): List<UnregistrationRequest> {
        val sql = "SELECT * FROM `unregistration_requests`;"
        logSQL(sql)
        val requests = secure { session.select(sql) { parseRow(it) } }
        return requests ?: emptyList()
    }

    private fun merge(sql: String, t: UnregistrationRequest): String {
        return sql.replace(":id" with t.id)
                .replace(":municipalServiceCertificateNumber" with t.municipalServiceCertificateNumber)
                .replace(":nationalPassportSeries" with t.nationalPassportSeries)
                .replace(":nationalPassportNumber" with t.nationalPassportNumber)
    }

    private fun parseRow(it: Row):UnregistrationRequest{
        return UnregistrationRequest(it.int("id"),
                it.string("municipal_service_certificate_number"),
                it.string("national_passport_series"),
                it.int("national_passport_number")
        )
    }
}