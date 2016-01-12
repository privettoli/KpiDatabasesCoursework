package ua.kpi.databases.coursework.ui

import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage
import ua.kpi.databases.coursework.WorkerQualification
import ua.kpi.databases.coursework.dao.WorkerQualificationDAO
import ua.kpi.databases.coursework.i18n


object WorkerQualificationScene {
    fun show() {
        val plantsTable: VBox = SmartTableView.createTableEditor(WorkerQualificationDAO, WorkerQualification::class) {
            prefWidth = when (it) {
                WorkerQualification::qualificationName -> 100.0
                WorkerQualification::salaryByHourUAH -> 100.0
                else -> prefWidth
            }
        }
        Stage().apply {
            title = "stage.worker.qualifications.title".i18n()
            icons.add(Image("icon.png"))
            scene = Scene((plantsTable).apply {
                spacing = 8.0
                padding = Insets(8.0)
                setPrefSize(384.0, 730.0)
            })
            show()
        }
    }
}