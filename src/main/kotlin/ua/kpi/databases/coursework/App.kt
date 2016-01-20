package ua.kpi.databases.coursework

import javafx.application.Application
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.ListView
import javafx.scene.image.Image
import javafx.stage.Stage
import nl.komponents.kovenant.Kovenant
import nl.komponents.kovenant.jfx.configureKovenant
import nl.komponents.kovenant.ui.promiseOnUi
import org.slf4j.LoggerFactory.getLogger
import ua.kpi.databases.coursework.dao.DBConnection
import ua.kpi.databases.coursework.ui.*

class App : Application() {
    companion object {
        val logger = getLogger(App.javaClass)
    }

    override fun start(primaryStage: Stage) {
        configureKovenant()
        promiseOnUi {
            val sqlExecutionsList = ListView<String>().apply {
                DBConnection.sqlLogCallback = { sqlAction ->
                    logger.debug("Executed SQL $sqlAction")
                    promiseOnUi {
                        items.add(sqlAction)
                    }
                }
                padding = Insets(8.0)
                setPrefSize(712.0, 986.0)
            }
            with (primaryStage) {
                title = "stage.sql.log.title".i18n()
                icons.add(Image("icon.png"))
                scene = Scene(sqlExecutionsList)
                x = 0.0
                y = 0.0
                onCloseRequest = EventHandler { e ->
                    Platform.setImplicitExit(true)
                    Platform.exit()
                    Kovenant.stop(force = true, block = true)
                }
                show()
            }
        }
        NationalPassportRequestScene.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}