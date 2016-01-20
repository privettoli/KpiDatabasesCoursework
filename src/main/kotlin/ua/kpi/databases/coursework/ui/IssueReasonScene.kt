package ua.kpi.databases.coursework.ui

import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage
import ua.kpi.databases.coursework.IssueReason
import ua.kpi.databases.coursework.dao.IssueReasonDAO
import ua.kpi.databases.coursework.i18n

object IssueReasonScene {
    fun show() {
        val table: VBox = SmartTableView.createTableEditor(IssueReasonDAO, IssueReason::class) {
            prefWidth = when (it) {
                IssueReason::name -> 100.0
                else -> prefWidth
            }
        }
        Stage().apply {
            title = "stage.issue.reason.title".i18n()
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