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
import kotlin.text.replaceFirst

class App : Application() {
    override fun start(primaryStage: Stage) {
        configureKovenant()
        promiseOnUi {
            val plantsTable = TableView<Plant>().apply {
                isEditable = true
                listOf("plant.id", "plant.name", "plant.area").forEach {
                    columns.add(TableColumn<Plant, String>(it.i18n()).apply {
                        cellValueFactory = PropertyValueFactory(it.removePrefix("plant."));
                        prefWidth = when (it) {
                            "plant.name" -> 500.0
                            else -> prefWidth
                        }
                    })
                }
            }
            val sqlExecutionsList = ListView<String>()
            val plantsScene = Scene(VBox(plantsTable, sqlExecutionsList).apply {
                spacing = 8.0
                padding = Insets(8.0)
                setPrefSize(1000.0, 500.0)
            })
            with (primaryStage) {
                title = "stage.title".i18n()
                icons.add(Image("icon.png"))
                scene = plantsScene
                show()
            }
            Pair(plantsTable, sqlExecutionsList)
        } then {
            Pair(it, loadPlants())
        } successUi {
            it.first.first.items.addAll(it.second.first)
            it.first.second.items.add(it.second.second)
        } then {
            println("Script ${it.second.second} executed, result ${it.second.first}")
        }
    }

    private fun loadPlants(): Pair<List<Plant>, String> {
        val url = "jdbc:mysql://localhost:3306/db_coursework?createDatabaseIfNotExist=false"
        val connection = getConnection(url, "root", "root")
        val session = DefaultSession(connection, MysqlDialect())
        val executedSql = "SELECT * FROM `plants`;"
        val plants = session.select(executedSql) {
            Plant(it.int("id"), it.string("name"), it.float("area_ares"))
        }
        return Pair(plants, executedSql)
    }
}

fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}