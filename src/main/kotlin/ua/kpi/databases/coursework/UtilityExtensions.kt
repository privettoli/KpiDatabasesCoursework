package ua.kpi.databases.coursework

import ua.kpi.databases.coursework.dao.DBConnection
import java.util.*
import kotlin.text.replaceFirst

val TRANSLATIONS = ResourceBundle.getBundle("translations/MainBundle")
public fun String.i18n(): String = TRANSLATIONS.getString(this)

public fun <V> secure(body: () -> V): V? {
    try {
        return body()
    } catch (e: Exception) {
        DBConnection.logSQL(e.message ?: "error.unexpected".i18n())
        return null
    }
}

public infix fun String.replace(pair: Pair<String, String>): String {
    return this.replaceFirst(pair.first, pair.second)
}

public infix fun String.with(that: Any?): Pair<String, String> {
    return Pair(this, "'${that.toString()}'")
}