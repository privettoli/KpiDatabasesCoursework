package ua.kpi.databases.coursework.ui

import javafx.beans.value.ChangeListener
import javafx.collections.FXCollections.observableArrayList
import javafx.collections.ListChangeListener
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.*
import javafx.scene.control.Alert.AlertType.ERROR
import javafx.scene.control.SelectionMode.MULTIPLE
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.control.cell.TextFieldTableCell
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.util.converter.FloatStringConverter
import javafx.util.converter.IntegerStringConverter
import nl.komponents.kovenant.task
import nl.komponents.kovenant.ui.promiseOnUi
import nl.komponents.kovenant.ui.successUi
import org.slf4j.LoggerFactory
import ua.kpi.databases.coursework.dao.DAO
import ua.kpi.databases.coursework.i18n
import ua.kpi.databases.coursework.replace
import ua.kpi.databases.coursework.with
import java.util.Collections.singletonList
import kotlin.collections.*
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.kotlinProperty
import kotlin.text.*

object SmartTableView {
    val logger = LoggerFactory.getLogger(javaClass)
    val intPattern = Regex("[0-9]*")
    val floatPattern = Regex("[0-9]*\\.?[0-9]*")

    fun <T> createTableEditor(dao: DAO<T>, type: KClass<out Any>, prefWidth: TableView<T>.(KMutableProperty1<T, *>) -> Unit): VBox {
        val fields = type.java.declaredFields.map { it.kotlinProperty as KMutableProperty1<T, out Any> }
        val tableView = TableView<T>().apply {
            isEditable = true
            selectionModel.selectionMode = MULTIPLE
            columns.addAll(fields.map { field ->
                createTableColumn(dao, field).apply { prefWidth(field) }
            })
            loadData(dao, this)
        }
        val controls = createControlsForTable(tableView, dao, type, fields)
        return VBox(tableView, controls)
    }

    private fun <T> loadData(dao: DAO<T>, table: TableView<T>) {
        task {
            dao.findAll()
        } successUi { entities ->
            table.items = observableArrayList<T>(entities).apply {
                addListener(tableChangeListener(dao, table))
            }
        }
    }

    private fun isValidValue(value: String, pattern: Regex?): Boolean {
        return when {
            pattern != null -> value.matches(pattern)
            else -> true
        }
    }

    public fun textPropertyListener(textField: TextField, pattern: Regex, defaultValue: Any): ChangeListener<String> {
        return ChangeListener { value, old, new ->
            if (!isValidValue(new, pattern)) {
                textField.text = when {
                    isValidValue(old, pattern) -> old
                    else -> defaultValue.toString()
                }
            }
        }
    }

    private fun <T> createControlsForTable(table: TableView<T>, dao: DAO<T>, type: KClass<out Any>, fields: Collection<KMutableProperty1<T, out Any>>): Node {
        val inputs = createInputs(dao, fields)
        val buttonAdd = createButtonAdd(fields, inputs, table, type)
        val buttonRemove = Button("button.remove".i18n()).apply {
            onAction = EventHandler { e ->
                table.items.removeAll(table.selectionModel.selectedItems)
            }
        }
        val buttonRefresh = Button("button.refresh".i18n()).apply {
            onAction = EventHandler { loadData(dao, table) }
        }
        return VBox().apply {
            spacing = 8.0
            padding = Insets(8.0)
            children.addAll(inputs)
            children.add(HBox(buttonAdd, buttonRemove, buttonRefresh))
        }
    }

    private fun <T> createInputs(dao: DAO<T>, fields: Collection<KMutableProperty1<T, out Any>>): List<TextField> {
        return fields.filterNot { it.name.equals("id") }.map { field ->
            TextField().apply {
                id = field.name
                promptText = (dao.prefix() + field.name).i18n();
                when (field.javaField?.type) {
                    Int::class.javaObjectType, Int::class.javaPrimitiveType -> {
                        textProperty().addListener(textPropertyListener(this, intPattern, 0))
                    }
                    Float::class.javaObjectType, Float::class.javaPrimitiveType -> {
                        textProperty().addListener(textPropertyListener(this, floatPattern, 0.0))
                    }
                }
            }
        }
    }

    private fun <T> createButtonAdd(fields: Collection<KMutableProperty1<T, out Any>>, inputs: List<TextField>, table: TableView<T>, type: KClass<out Any>): Button {
        return Button("button.add".i18n()).apply {
            onAction = EventHandler { e ->
                val parameters: Array<Any?> = idColumnIfContains(fields).plus(inputs.map { input ->
                    val property = fields.find { it.name == input.id }
                    val text = input.text
                    input.clear()
                    println("${property?.name} = $text")
                    when (property?.javaField?.type) {
                        Int::class.javaObjectType, Int::class.javaPrimitiveType -> when {
                            text.isNullOrBlank() -> {
                                alert(input.promptText)
                                return@EventHandler
                            }
                            else -> text.toInt()
                        }
                        Float::class.javaObjectType, Float::class.javaPrimitiveType -> when {
                            text.isNullOrBlank() -> {
                                alert(input.promptText)
                                return@EventHandler
                            }
                            else -> text.toFloat()
                        }
                        else -> text
                    }
                }).toTypedArray()
                println("Parameters ${parameters.map { "" + it }} Size: ${parameters.size}")
                val new = type.constructors.first().call(*parameters)
                println("Adding new element $new")
                table.items.add(new as T)
            }
        }
    }

    private fun alert(property: String) {
        promiseOnUi {
            Alert(ERROR).apply {
                title = "alert.not.filled.input.title".i18n()
                headerText = "alert.not.filled.input.header.text".i18n().replace(":field" with property)
                contentText = "alert.not.filled.input.contentText".i18n().replace(":field" with property)
                showAndWait();
            }
        }
        throw IllegalStateException("Property $property should not be null")
    }

    private fun <T> idColumnIfContains(fields: Collection<KMutableProperty1<T, out Any>>): Collection<Any?> {
        return when {
            fields.find { it.name == "id" } != null -> singletonList(null)
            else -> emptyList()
        }
    }

    private fun <T> tableChangeListener(dao: DAO<T>, table: TableView<T>): ListChangeListener<T> {
        return ListChangeListener { e ->
            task {
                println("Changing $e")
                do {
                    e.addedSubList.forEach { dao.insert(it) }
                    e.removed.forEach { dao.remove(it) }
                } while (e.next())
                loadData(dao, table)
            }
        }
    }

    fun <T> createTableColumn(dao: DAO<T>, field: KMutableProperty1<T, out Any>): TableColumn<T, *> {
        val fieldType = field.javaField?.type
        return when (fieldType) {
            Int::class.javaObjectType, Int::class.javaPrimitiveType -> TableColumn<T, Int>()
            Float::class.javaObjectType, Float::class.javaPrimitiveType -> TableColumn<T, Float>()
            else -> TableColumn<T, String>()
        }.apply {
            cellFactory = when (fieldType) {
                Int::class.javaObjectType, Int::class.javaPrimitiveType -> TextFieldTableCell.forTableColumn(IntegerStringConverter())
                Float::class.javaObjectType, Float::class.javaPrimitiveType -> TextFieldTableCell.forTableColumn(FloatStringConverter())
                else -> TextFieldTableCell.forTableColumn()
            }
            onEditCancel = EventHandler { e ->
                field.setter.call(e.rowValue, e.oldValue)
            }
            onEditCommit = EventHandler { e ->
                field.setter.call(e.rowValue, e.newValue)
                task {
                    dao.update(e.rowValue)
                } fail { exception ->
                    logger.error("Can't update cell to ${e.newValue}", exception)
                    promiseOnUi { this.textProperty().value = e.oldValue.toString() }
                    field.setter.call(e.rowValue, e.oldValue)
                }
            }
            cellValueFactory = PropertyValueFactory(field.name)
            text = (dao.prefix() + field.name).i18n()
            isEditable = when (field.name) {
                "id", "techOperation", "workerQualification" -> false
                else -> true
            }
        }
    }
}