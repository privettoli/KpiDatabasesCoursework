package ua.kpi.databases.coursework.dao

import com.github.andrewoma.kwery.core.Row
import ua.kpi.databases.coursework.RegistrationRequest
import ua.kpi.databases.coursework.dao.DBConnection.logSQL
import ua.kpi.databases.coursework.dao.DBConnection.session
import ua.kpi.databases.coursework.replace
import ua.kpi.databases.coursework.secure
import ua.kpi.databases.coursework.with
import kotlin.collections.emptyList
import kotlin.collections.firstOrNull
import kotlin.collections.mapOf

object RegistrationRequestDAO : DAO<RegistrationRequest> {
    override fun prefix(): String {
        return "registration.request."
    }

    override fun insert(t: RegistrationRequest) {
        val sql = "INSERT INTO `registration_requests` (`unregistration_request_id`, " +
                "`registration_permit_number`, `national_passport_series`, `national_passport_number`) " +
                "VALUES (:unregistrationRequestId, :registrationPermitNumber, " +
                ":nationalPassportSeries, :nationalPassportNumber);"
        logSQL(merge(sql, t))
        secure { session.insert(sql, mapper(t)) { } }
    }

    private fun mapper(t: RegistrationRequest): Map<String, Any?> {
        return mapOf("id" to t.id,
                "unregistrationRequestId" to t.unregistrationRequestId,
                "registrationPermitNumber" to t.registrationPermitNumber,
                "nationalPassportSeries" to t.nationalPassportSeries,
                "nationalPassportNumber" to t.nationalPassportNumber)
    }

    override fun remove(t: RegistrationRequest) {
        val sql = "DELETE FROM `registration_requests` WHERE `id` = :id ;"
        logSQL(sql.replace(":id" with t.id.toString()))
        secure { session.update(sql, mapOf("id" to t.id)) }
    }

    override fun update(t: RegistrationRequest) {
        val sql = "UPDATE `registration_requests` SET " +
                "`unregistration_request_id` = :unregistrationRequestId, " +
                "`registration_permit_number` = :registrationPermitNumber, " +
                "`national_passport_series` = :nationalPassportSeries, " +
                "`national_passport_number` = :nationalPassportNumber "  +
                "WHERE `id` = :id;"
        logSQL(merge(sql, t))
        secure { session.update(sql, mapper(t)) }
    }

    override fun findBy(id: Int): RegistrationRequest? {
        val sql = "SELECT * FROM `registration_requests` WHERE `id` = :id;"
        logSQL(sql.replace(":id" with id))
        val registration_request = secure { session.select(sql, mapOf("id" to id)) { parseRow(it) } }
        return registration_request?.firstOrNull()
    }

    override fun findAll(): List<RegistrationRequest> {
        val sql = "SELECT * FROM `registration_requests`;"
        logSQL(sql)
        val requests = secure { session.select(sql) { parseRow(it) } }
        return requests ?: emptyList()
    }

    private fun merge(sql: String, t: RegistrationRequest): String {
        return sql.replace(":id" with t.id)
                .replace(":unregistrationRequestId" with t.unregistrationRequestId)
                .replace(":registrationPermitNumber" with t.registrationPermitNumber)
                .replace(":nationalPassportSeries" with t.nationalPassportSeries)
                .replace(":nationalPassportNumber" with t.nationalPassportNumber)
    }

    private fun parseRow(it: Row):RegistrationRequest{
        return RegistrationRequest(it.int("id"),
                it.int("unregistration_request_id"),
                it.string("registration_permit_number"),
                it.string("national_passport_series"),
                it.int("national_passport_number")
        )
    }
}