package ua.kpi.databases.coursework

import java.util.*

val TRANSLATIONS = ResourceBundle.getBundle("translations/MainBundle")
public fun String.i18n(): String = TRANSLATIONS.getString(this)