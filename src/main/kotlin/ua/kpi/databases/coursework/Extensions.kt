package ua.kpi.databases.coursework

import java.util.*

val translations = ResourceBundle.getBundle("translations/MainBundle")
public fun String.i18n(): String = translations.getString(this)