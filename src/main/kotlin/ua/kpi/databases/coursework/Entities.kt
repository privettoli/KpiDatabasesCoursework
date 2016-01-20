package ua.kpi.databases.coursework

/**
 * Created by privettoli@gmail.com
 * Here are listed entities of the project
 */

data class NationalPassportRequest(var id: Int?, var municipalServiceCertificateNumber: String, var birthCertificateId: Int, var photo: Int, var issueReasonId: Int, var penaltyReceiptId: Int?, var policeConfirmationNumber: Int?)

data class BirthCertificate(var id: Int?, var series: String, var number: Int, var date: String)

data class PenaltyReceipt(var id: Int?, var typeId: Int, var tax: Float, var sum: Float)

data class PenaltyReceiptType(var id: Int?, var name: String)

data class IssueReason(var id: Int?, var name: String)

data class ForeignPassportRequest(var id: Int?, var nationalPassportSeries: String, var nationalPassportNumber: Int, var convictionAbsenceCertificateNumber: String, var militaryCertificateNumber: String)

data class RegistrationRequest(var id: Int?, var unregistrationRequestId: Int, var registrationPermitNumber: String, var nationalPassportSeries: String, var nationalPassportNumber: Int, var convictionAbsenceCertificateNumber: String, var militaryCertificateNumber: String)

data class UnregistrationRequest(var id: Int?, var municipalServiceCertificateNumber: String, var nationalPassportSeries: String, var nationalPassportNumber: Int)