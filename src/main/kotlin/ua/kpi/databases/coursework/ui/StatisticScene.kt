package ua.kpi.databases.coursework.ui

import javafx.collections.FXCollections
import javafx.collections.FXCollections.observableArrayList
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage
import nl.komponents.kovenant.task
import nl.komponents.kovenant.ui.successUi
import ua.kpi.databases.coursework.dao.StatisticDAO
import ua.kpi.databases.coursework.i18n
import ua.kpi.databases.coursework.sqlExecutionExceptionAlert
import ua.kpi.databases.coursework.ui.SmartTableView.intPattern
import ua.kpi.databases.coursework.ui.SmartTableView.textPropertyListener
import kotlin.collections.map
import kotlin.text.isNotBlank
import kotlin.text.toInt

object StatisticScene {
    fun show() {
        Stage().apply {
            title = "stage.statistic.title".i18n()
            icons.add(Image("icon.png"))
            scene = Scene(VBox().apply {
                val inputPlantId = TextField().apply {
                    promptText = "statistic.input.plant".i18n()
                    textProperty().addListener(textPropertyListener(this, intPattern, 0))
                }
                val statisticTable = TableView<Pair<Int, Int>>().apply {
                    columns.add(TableColumn<Pair<Int, Int>, Int>("statistic.column.month".i18n()).apply {
                        cellValueFactory = PropertyValueFactory(Pair<Int, Int>::first.name)
                    })
                    columns.add(TableColumn<Pair<Int, Int>, Int>("statistic.column.sum".i18n()).apply {
                        cellValueFactory = PropertyValueFactory(Pair<Int, Int>::second.name)
                    })
                }
                val buttonShow = Button("button.show".i18n()).apply {
                    onAction = EventHandler {
                        val text = inputPlantId.text
                        if (text.isNotBlank()) {
                            task {
                                StatisticDAO.calculateSumForPlant(text.toInt())
                            } successUi {
                                statisticTable.items = observableArrayList<Pair<Int, Int>>(it)
                            } fail {
                                sqlExecutionExceptionAlert(it)
                            }
                        }
                    }
                }
                children.add(inputPlantId)
                children.add(buttonShow)
                children.add(statisticTable)
                spacing = 8.0
                padding = Insets(8.0)
                setPrefSize(1000.0, 730.0)
            })
            show()
        }
    }
}