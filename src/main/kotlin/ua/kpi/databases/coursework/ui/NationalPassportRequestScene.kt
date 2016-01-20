package ua.kpi.databases.coursework.ui

import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage
import ua.kpi.databases.coursework.NationalPassportRequest
import ua.kpi.databases.coursework.dao.NationalPassportRequestDAO
import ua.kpi.databases.coursework.i18n

object NationalPassportRequestScene {
    fun show() {
        val table: VBox = SmartTableView.createTableEditor(NationalPassportRequestDAO, NationalPassportRequest::class) {
            prefWidth = when (it) {
                NationalPassportRequest::policeConfirmationNumber -> 100.0
                else -> prefWidth
            }
        }
        Stage().apply {
            title = "stage.national.passport.request.title".i18n()
            icons.add(Image("icon.png"))
            scene = Scene((table).apply {
                spacing = 8.0
                padding = Insets(8.0)
                setPrefSize(730.0, 384.0)
            })
            show()
        }
    }
}