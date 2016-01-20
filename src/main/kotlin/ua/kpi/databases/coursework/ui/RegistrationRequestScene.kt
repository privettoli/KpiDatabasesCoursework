package ua.kpi.databases.coursework.ui

import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage
import ua.kpi.databases.coursework.RegistrationRequest
import ua.kpi.databases.coursework.dao.RegistrationRequestDAO
import ua.kpi.databases.coursework.i18n

object RegistrationRequestScene {
    fun show() {
        val table: VBox = SmartTableView.createTableEditor(RegistrationRequestDAO, RegistrationRequest::class) {
            prefWidth = when (it) {
                RegistrationRequest::nationalPassportNumber -> 100.0
                else -> prefWidth
            }
        }
        Stage().apply {
            title = "stage.registration.request.title".i18n()
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