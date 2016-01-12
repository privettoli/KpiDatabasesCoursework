package ua.kpi.databases.coursework.dao

import com.github.andrewoma.kwery.core.Row
import ua.kpi.databases.coursework.TechOperationWorkerQualification
import ua.kpi.databases.coursework.dao.DBConnection.logSQL
import ua.kpi.databases.coursework.dao.DBConnection.session
import ua.kpi.databases.coursework.replace
import ua.kpi.databases.coursework.secure
import ua.kpi.databases.coursework.with
import kotlin.collections.emptyList
import kotlin.collections.mapOf

object TechOperationWorkerQualificationDAO : DAO<TechOperationWorkerQualification> {
    override fun findAll(): List<TechOperationWorkerQualification> {
        val sql = "SELECT * FROM `worker_qualifications_tech_operations`;"
        logSQL(sql)
        val plants = secure { session.select(sql) { parseRow(it) } }
        return plants ?: emptyList()
    }

    override fun update(t: TechOperationWorkerQualification) {
        secure { throw UnsupportedOperationException() }
    }

    override fun findBy(id: Int): TechOperationWorkerQualification? {
        return secure { throw UnsupportedOperationException() }
    }

    override fun remove(t: TechOperationWorkerQualification) {
        val sql = "DELETE FROM `worker_qualifications_tech_operations` " +
                "WHERE `tech_operation_id` = :techOperation AND `worker_qualification_id` = :workerQualification;"
        logSQL(merge(sql, t))
        secure { session.update(sql, mapper(t)) }
    }

    override fun prefix(): String {
        return "worker.qualification.tech.operations."
    }

    override fun insert(t: TechOperationWorkerQualification) {
        val sql = "INSERT INTO `worker_qualifications_tech_operations` (`tech_operation_id`, `worker_qualification_id`) " +
                "VALUES (:techOperation, :workerQualification);"
        logSQL(merge(sql, t))
        secure { session.insert(sql, mapper(t)) { } }
    }

    private fun mapper(t: TechOperationWorkerQualification) = mapOf(
            "techOperation" to t.techOperation, "workerQualification" to t.workerQualification)

    private fun merge(sql: String, t: TechOperationWorkerQualification): String {
        return sql.replace(":techOperation" with t.techOperation)
                .replace(":workerQualification" with t.workerQualification)
    }

    private fun parseRow(it: Row) = TechOperationWorkerQualification(it.int("tech_operation_id"), it.int("worker_qualification_id"))
}