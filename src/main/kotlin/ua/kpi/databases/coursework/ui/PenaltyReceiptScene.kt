package ua.kpi.databases.coursework.ui

import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage
import ua.kpi.databases.coursework.PenaltyReceipt
import ua.kpi.databases.coursework.dao.PenaltyReceiptDAO
import ua.kpi.databases.coursework.i18n

object PenaltyReceiptScene {
    fun show() {
        val table: VBox = SmartTableView.createTableEditor(PenaltyReceiptDAO, PenaltyReceipt::class) {
            prefWidth = when (it) {
                PenaltyReceipt::sum -> 100.0
                else -> prefWidth
            }
        }
        Stage().apply {
            title = "stage.penalty.receipt.title".i18n()
            icons.add(Image("icon.png"))
            scene = Scene((table).apply {
                spacing = 8.0
                padding = Insets(8.0)
                setPrefSize(384.0, 730.0)
            })
            show()
        }
    }
}