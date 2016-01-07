package ua.kpi.databases.coursework

import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage

class App : Application() {
    override fun start(stage: Stage) {
        val editText = TextField().apply {
            promptText = "editText.text".i18n()
        }
        val completionsList = ListView<String>()
        val mainVBox = VBox(editText, completionsList).apply {
            spacing = 8.0
            padding = Insets(8.0)
        }
        stage.apply {
            title = "stage.title".i18n()
            icons.add(Image("icon.png"))
            scene = Scene(mainVBox)
            show()
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}