package ua.kpi.databases.coursework

/**
 * Created by privettoli@gmail.com
 * Here are listed entities of the project
 */

data class NationalPassportRequest (var id: Int?, var municipalServiceCertificateNumber: String, var birthCertificateSeries: String, var birthCertificateNumber: Int, var photo: Int, var issueReasonId: Int?, var penaltyReceiptId: Int?, var policeConfirmation: Int)

data class PenaltyReceipt(var id: Int?, var tax: Float, var sum: Float)

data class IssueReason(var id: Int?, var name: String)

data class ForeignPassportRequest(var id: Int?, var nationalPassportSeries: String, var nationalPassportNumber: Int, var convictionAbsenceCertificateNumber: String, var militaryCertificateNumber: String)

data class RegistrationRequest(var id: Int?, var unregistrationRequestId: Int, var registrationPermitNumber: String, var nationalPassportSeries: String, var nationalPassportNumber: Int, var convictionAbsenceCertificateNumber: String, var militaryCertificateNumber: String)

data class UnregistrationRequest(var id: Int?, var municipalServiceCertificateNumber: String, var nationalPassportSeries: String, var nationalPassportNumber: Int)