package ua.kpi.databases.coursework.ui

import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage
import ua.kpi.databases.coursework.TechOperation
import ua.kpi.databases.coursework.TechOperationWorkerQualification
import ua.kpi.databases.coursework.dao.TechOperationWorkerQualificationDAO
import ua.kpi.databases.coursework.i18n
import ua.kpi.databases.coursework.ui.SmartTableView.createTableEditor

object TechOperationWorkerQualificationScene {
    fun show() {
        val table: VBox = createTableEditor(TechOperationWorkerQualificationDAO,
                TechOperationWorkerQualification::class) {
            prefWidth = when (it) {
                else -> prefWidth
            }
        }
        Stage().apply {
            title = "stage.worker.qualification.tech.operations.title".i18n()
            icons.add(Image("icon.png"))
            scene = Scene((table).apply {
                spacing = 8.0
                padding = Insets(8.0)
                setPrefSize(300.0, 300.0)
            })
            show()
        }
    }
}