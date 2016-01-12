package ua.kpi.databases.coursework

import javafx.collections.FXCollections
import javafx.collections.FXCollections.observableArrayList
import javafx.collections.ListChangeListener
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.control.cell.TextFieldTableCell
import javafx.scene.layout.VBox
import nl.komponents.kovenant.task
import nl.komponents.kovenant.ui.successUi
import kotlin.collections.forEach
import kotlin.collections.listOf
import kotlin.text.removePrefix

fun <T> createTableEditor(dao: DAO<T>, columnNames: List<String>, columnPrefix: String,
                          prefWidth: TableView<T>.(String) -> Unit): TableView<T> {
    return TableView<T>().apply {
        isEditable = true
        columnNames.forEach {
            columns.add(TableColumn<T, String>(it.i18n()).apply {
                cellValueFactory = PropertyValueFactory(it.removePrefix(columnPrefix))
                cellFactory.run {
                    isEditable = true
                }
                isEditable = true
                prefWidth(it)
            })
        }
        task {
            dao.findAll()
        } successUi { entities ->
            items.addAll(entities)
        }
        items.addListener(ListChangeListener { e ->
            while (e.next()) {
                task {
                    when {
                        e.wasAdded() -> e.addedSubList.forEach { dao.update(it) }
                        e.wasRemoved() -> e.removed.forEach { dao.remove(it) }
                        e.wasUpdated() ->
                            for (i in e.from..(e.to - 1)) {
                                dao.update(e.list[i])
                            }
                        else -> {
                        }
                    }
                }
            }
        })
    }
}

object PlantScene {
    val scene: Scene = {
        val plantsTable: TableView<PlantEntity> = createTableEditor(PlantDAO, listOf("plant.id", "plant.name", "plant.area"), "plant.") {
            prefWidth = when (it) {
                "plant.name" -> 500.0
                else -> prefWidth
            }
        }
        Scene(VBox(plantsTable).apply {
            spacing = 8.0
            padding = Insets(8.0)
            setPrefSize(1000.0, 500.0)
        })
    }()
}