package ua.kpi.databases.coursework

import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage
import javafx.stage.StageStyle

class App : Application() {
    override fun start(stage: Stage) {
        val editText = TextField("editText.text".i18n())
        val completionsList = ListView<String>()

        val vbox = VBox(editText, completionsList).apply {
            spacing = 8.0
            padding = Insets(8.0)
        }
        stage.title = "stage.title".i18n()
        stage.icons.add(Image("/icon.png"))
        stage.scene = Scene(vbox)
        stage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}