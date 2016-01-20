package ua.kpi.databases.coursework.ui

import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage
import ua.kpi.databases.coursework.ForeignPassportRequest
import ua.kpi.databases.coursework.dao.ForeignPassportRequestDAO
import ua.kpi.databases.coursework.i18n

object ForeignPassportRequestScene {
    fun show() {
        val table: VBox = SmartTableView.createTableEditor(ForeignPassportRequestDAO, ForeignPassportRequest::class) {
            prefWidth = when (it) {
                ForeignPassportRequest::militaryCertificateNumber -> 100.0
                else -> prefWidth
            }
        }
        Stage().apply {
            title = "stage.foreign.passport.request.title".i18n()
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