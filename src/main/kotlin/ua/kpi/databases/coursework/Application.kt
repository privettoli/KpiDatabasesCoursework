package ua.kpi.databases.coursework

import com.github.andrewoma.kwery.core.DefaultSession
import com.github.andrewoma.kwery.core.dialect.MysqlDialect
import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.ListView
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage
import nl.komponents.kovenant.jfx.configureKovenant
import nl.komponents.kovenant.then
import nl.komponents.kovenant.ui.promiseOnUi
import nl.komponents.kovenant.ui.successUi
import java.sql.DriverManager.getConnection
import kotlin.collections.forEach
import kotlin.collections.listOf
import kotlin.text.removePrefix

class App : Application() {
    override fun start(primaryStage: Stage) {
        configureKovenant()
        promiseOnUi {
            val sqlExecutionsList = ListView<String>().apply {
                DBConnection.sqlLogCallback = { sqlAction ->
                    promiseOnUi { items.add(sqlAction) }
                }
                padding = Insets(8.0)
                setPrefSize(1000.0, 500.0)
            }
            Stage().apply {
                title = "stage.plants.title".i18n()
                icons.add(Image("icon.png"))
                scene = PlantScene.scene
                show()
            }
            with (primaryStage) {
                title = "stage.sql.log.title".i18n()
                icons.add(Image("icon.png"))
                scene = Scene(sqlExecutionsList)
                show()
            }
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}