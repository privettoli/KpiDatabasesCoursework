package ua.kpi.databases.coursework.ui

import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage
import ua.kpi.databases.coursework.BirthCertificate
import ua.kpi.databases.coursework.dao.BirthCertificateDAO
import ua.kpi.databases.coursework.i18n

object BirthCertificateScene {
    fun show() {
        val table: VBox = SmartTableView.createTableEditor(BirthCertificateDAO, BirthCertificate::class) {
            prefWidth = when (it) {
                BirthCertificate::date -> 100.0
                else -> prefWidth
            }
        }
        Stage().apply {
            title = "stage.birth.certificate.title".i18n()
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