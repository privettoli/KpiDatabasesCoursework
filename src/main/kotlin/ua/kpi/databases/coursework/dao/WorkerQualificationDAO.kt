package ua.kpi.databases.coursework.dao

import com.github.andrewoma.kwery.core.Row
import ua.kpi.databases.coursework.WorkerQualification
import ua.kpi.databases.coursework.dao.DBConnection.logSQL
import ua.kpi.databases.coursework.dao.DBConnection.session
import ua.kpi.databases.coursework.replace
import ua.kpi.databases.coursework.secure
import ua.kpi.databases.coursework.with
import kotlin.collections.emptyList
import kotlin.collections.firstOrNull
import kotlin.collections.mapOf

object WorkerQualificationDAO : DAO<WorkerQualification> {
    override fun findAll(): List<WorkerQualification> {
        val sql = "SELECT * FROM `worker_qualifications`;"
        logSQL(sql)
        val plants = secure { session.select(sql) { parseRow(it) } }
        return plants ?: emptyList()
    }

    override fun update(t: WorkerQualification) {
        val sql = "UPDATE `worker_qualifications` SET " +
                "`qualification_name` = :qualificationName," +
                "`salary_by_hour_uah` = :salaryByHourUAH " +
                "WHERE `id` = :id;"
        logSQL(merge(sql, t))
        secure { session.update(sql, mapper(t)) }
    }

    override fun findBy(id: Int): WorkerQualification? {
        val sql = "SELECT * FROM `worker_qualifications` WHERE `id` = :id;"
        DBConnection.logSQL(sql.replace(":id" with id))
        val plant = secure { DBConnection.session.select(sql, mapOf("id" to id)) { parseRow(it) } }
        return plant?.firstOrNull()
    }

    override fun remove(t: WorkerQualification) {
        val sql = "DELETE FROM `worker_qualifications` WHERE `id` = :id ;"
        logSQL(sql.replace(":id" with t.id.toString()))
        secure { session.update(sql, mapOf("id" to t.id)) }
    }

    override fun insert(t: WorkerQualification) {
        val sql = "INSERT INTO `worker_qualifications` (`qualification_name`, `salary_by_hour_uah`) " +
                "VALUES (:qualificationName, :salaryByHourUAH);"
        logSQL(merge(sql, t))
        secure { session.insert(sql, mapper(t)) { } }
    }

    override fun prefix(): String {
        return "worker.qualification."
    }


    private fun mapper(t: WorkerQualification) = mapOf("id" to t.id, "qualificationName" to t.qualificationName,
            "salaryByHourUAH" to t.salaryByHourUAH)

    private fun merge(sql: String, t: WorkerQualification): String {
        return sql.replace(":id" with t.id)
                .replace(":qualificationName" with t.qualificationName)
                .replace(":salaryByHourUAH" with t.salaryByHourUAH)
    }

    private fun parseRow(it: Row) = WorkerQualification(it.int("id"), it.string("qualification_name"),
            it.int("salary_by_hour_uah"))
}