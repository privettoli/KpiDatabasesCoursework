package ua.kpi.databases.coursework

import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType.ERROR
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.layout.GridPane
import javafx.scene.layout.GridPane.setHgrow
import javafx.scene.layout.GridPane.setVgrow
import javafx.scene.layout.Priority.ALWAYS
import nl.komponents.kovenant.ui.promiseOnUi
import org.slf4j.LoggerFactory
import ua.kpi.databases.coursework.dao.DBConnection
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*
import kotlin.text.replaceFirst

val logger = LoggerFactory.getLogger("UtilityExtensions.kt")

val TRANSLATIONS = ResourceBundle.getBundle("translations/MainBundle")
public fun String.i18n(): String = TRANSLATIONS.getString(this)

public fun <V> secure(body: () -> V): V? {
    try {
        return body()
    } catch (e: Exception) {
        val msg = e.message ?: "error.unexpected".i18n()
        logger.error(msg, e)
        DBConnection.logSQL(msg)
        promiseOnUi { sqlExecutionExceptionAlert(e).showAndWait() }
        return null
    }
}

public infix fun String.replace(pair: Pair<String, String>): String {
    return this.replaceFirst(pair.first, pair.second)
}

public infix fun String.with(that: Any?): Pair<String, String> {
    return Pair(this, when (that) {
        null -> "NULL"
        else -> "'${that.toString()}'"
    })
}

public fun sqlExecutionExceptionAlert(e: Exception): Alert {
    return Alert(ERROR).apply {
        val msg = e.message ?: "error.unexpected".i18n()
        title = "alert.sql.exception.title".i18n()
        headerText = "alert.sql.exception.header.text".i18n().replace(":msg" with msg)
        dialogPane.expandableContent = GridPane().apply {
            maxWidth = Double.MAX_VALUE
            add(Label("alert.sql.exception.label.text".i18n()), 0, 0)
            add(TextArea(stackTrace(e)).apply {
                isEditable = false
                isWrapText = true
                maxWidth = Double.MAX_VALUE
                maxHeight = Double.MAX_VALUE
                setVgrow(this, ALWAYS)
                setHgrow(this, ALWAYS)
            }, 0, 1)
        }
    }
}

private fun stackTrace(e: Exception): String {
    val sw = StringWriter()
    val pw = PrintWriter(sw)
    e.printStackTrace(pw)
    return sw.toString()
}