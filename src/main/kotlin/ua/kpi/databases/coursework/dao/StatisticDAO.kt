/*
package ua.kpi.databases.coursework.dao

import ua.kpi.databases.coursework.replace
import ua.kpi.databases.coursework.secure
import ua.kpi.databases.coursework.with
import kotlin.collections.emptyList
import kotlin.collections.mapOf

object StatisticDAO {
    fun calculateSumForPlant(plantId: Int): List<Pair<Int, Int>> {
        val sql = """SELECT `month`, SUM(`salary_by_hour_uah` * `processing_time_weeks` * 40) AS 'sum'
    FROM `worker_qualifications_tech_operations`
    JOIN `worker_qualifications` ON `worker_qualifications`.`id` = `worker_qualifications_tech_operations`.`worker_qualification_id`
    JOIN `tech_operations` ON `tech_operations`.`id` = `worker_qualifications_tech_operations`.`tech_operation_id`
    WHERE `tech_operations`.`plant_id` = :plantId
    GROUP BY `tech_operations`.`month`;"""
        DBConnection.logSQL(sql.replace(":plantId" with plantId))
        return secure {
            DBConnection.session.select(sql, mapOf("plantId" to plantId)) {
                Pair(it.int("month"), it.int("sum"))
            }
        } ?: emptyList()
    }
}*/
