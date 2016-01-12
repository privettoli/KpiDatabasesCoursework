package ua.kpi.databases.coursework.dao

interface DAO<T> {
    fun findAll(): List<T>
    fun update(t: T)
    fun findBy(id: Int): T?
    fun remove(t: T)
    fun prefix(): String
    fun insert(t: T)
}