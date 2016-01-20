package ua.kpi.databases.coursework.dao

import com.github.andrewoma.kwery.core.Row
import ua.kpi.databases.coursework.NationalPassportRequest
import ua.kpi.databases.coursework.dao.DBConnection.logSQL
import ua.kpi.databases.coursework.dao.DBConnection.session
import ua.kpi.databases.coursework.replace
import ua.kpi.databases.coursework.secure
import ua.kpi.databases.coursework.with
import kotlin.collections.emptyList
import kotlin.collections.firstOrNull
import kotlin.collections.mapOf

object NationalPassportRequestDAO : DAO<NationalPassportRequest> {
    override fun prefix(): String {
        return "national.passport.request."
    }

    override fun insert(t: NationalPassportRequest) {
        val sql = "INSERT INTO `national_passport_requests` (`municipal_service_certificate_number`, " +
                "`birth_certificate_id`, `photo`, `issue_reason_id`, `penalty_receipt_id`, " +
                "`police_confirmation_number`) VALUES (:municipalServiceCertificateNumber, :birthCertificateId, " +
                ":photo, :issueReasonId, :penaltyReceiptId, :policeConfirmationNumber);"
        logSQL(merge(sql, t))
        secure { session.insert(sql, mapper(t)) { } }
    }

    private fun mapper(t: NationalPassportRequest): Map<String, Any?> {
        return mapOf("id" to t.id,
                "birthCertificateId" to t.birthCertificateId,
                "issueReasonId" to t.issueReasonId,
                "municipalServiceCertificateNumber" to t.municipalServiceCertificateNumber,
                "penaltyReceiptId" to t.penaltyReceiptId,
                "photo" to t.photo,
                "policeConfirmationNumber" to t.policeConfirmationNumber)
    }

    override fun remove(t: NationalPassportRequest) {
        val sql = "DELETE FROM `national_passport_requests` WHERE `id` = :id ;"
        logSQL(sql.replace(":id" with t.id.toString()))
        secure { session.update(sql, mapOf("id" to t.id)) }
    }

    override fun update(t: NationalPassportRequest) {
        val sql = "UPDATE `national_passport_requests` SET " +
                "`municipal_service_certificate_number` = :municipalServiceCertificateNumber, " +
                "`birth_certificate_id` = :birthCertificateId, " +
                "`photo` = :photo, " +
                "`issue_reason_id` = :issueReasonId, " +
                "`penalty_receipt_id` = :penaltyReceiptId, " +
                "`police_confirmation_number` = :policeConfirmationNumber " +
                "WHERE `id` = :id;"
        logSQL(merge(sql, t))
        secure { session.update(sql, mapper(t)) }
    }

    override fun findBy(id: Int): NationalPassportRequest? {
        val sql = "SELECT * FROM `national_passport_requests` WHERE `id` = :id;"
        logSQL(sql.replace(":id" with id))
        val plant = secure { session.select(sql, mapOf("id" to id)) { parseRow(it) } }
        return plant?.firstOrNull()
    }

    override fun findAll(): List<NationalPassportRequest> {
        val sql = "SELECT * FROM `national_passport_requests`;"
        logSQL(sql)
        val requests = secure { session.select(sql) { parseRow(it) } }
        return requests ?: emptyList()
    }

    private fun merge(sql: String, t: NationalPassportRequest): String {
        return sql.replace(":id" with t.id)
                .replace(":municipalServiceCertificateNumber" with t.municipalServiceCertificateNumber)
                .replace(":birthCertificateId" with t.birthCertificateId)
                .replace(":issueReasonId" with t.issueReasonId)
                .replace(":photo" with t.photo)
                .replace(":policeConfirmationNumber" with t.policeConfirmationNumber)
                .replace(":penaltyReceiptId" with t.penaltyReceiptId)
    }

    private fun parseRow(it: Row):NationalPassportRequest{
        return NationalPassportRequest(it.int("id"),
                it.string("municipal_service_certificate_number"),
                it.int("birth_certificate_id"),
                it.int("photo"),
                it.int("issue_reason_id"),
                it.intOrNull("penalty_receipt_id"),
                it.intOrNull("police_confirmation_number")
        )
    }
}