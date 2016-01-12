package ua.kpi.databases.coursework

/**
 * Created by privettoli@gmail.com
 * Here are listed entities of the project
 */

data class Plant(var id: Int?, var name: String, var area: Float)

data class TechOperation(var id: Int?, var month: Int, var name: String, var fuelConsumptionLiters: Float,
                         var processingTimeWeeks: Float, var plantId: Int)

data class WorkerQualification(var id: Int?, var qualificationName: String, var salaryByHourUAH: Int)

data class TechOperationWorkerQualification(var techOperation: Int, var workerQualification: Int)