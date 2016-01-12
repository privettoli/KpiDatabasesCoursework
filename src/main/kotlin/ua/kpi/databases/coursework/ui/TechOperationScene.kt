package ua.kpi.databases.coursework.ui

import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage
import ua.kpi.databases.coursework.TechOperation
import ua.kpi.databases.coursework.dao.TechOperationDAO
import ua.kpi.databases.coursework.i18n

object TechOperationScene {
    fun show() {
        val table: VBox = SmartTableView.createTableEditor(TechOperationDAO, TechOperation::class) {
            prefWidth = when (it) {
                TechOperation::name -> 200.0
                TechOperation::fuelConsumptionLiters -> 200.0
                TechOperation::processingTimeWeeks -> 200.0
                else -> prefWidth
            }
        }
        Stage().apply {
            title = "stage.tech.operations.title".i18n()
            icons.add(Image("icon.png"))
            scene = Scene((table).apply {
                spacing = 8.0
                padding = Insets(8.0)
                setPrefSize(1000.0, 730.0)
            })
            show()
        }
    }
}