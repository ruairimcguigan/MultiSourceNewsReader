package com.aquidigital.multisource.repository

import io.realm.RealmModel
import io.realm.RealmQuery
import io.realm.Sort
import java.io.Closeable

interface Repository <T> :Closeable where T : RealmModel {

    fun getById(d: String): T?

    fun add(item: T)

    suspend fun addAll(items: List<T>)

    suspend fun delete(filter: RealmQuery<T>.() -> Unit)

    suspend fun deleteAll()

    fun update(id: String, modifier: T.() -> Unit)

    fun count(): Long

    fun count(filter: RealmQuery<T>.() -> Unit): Long

    suspend fun query(filter: RealmQuery<T>.() -> Unit, sortFields: Array<String>?, orders: Array<Sort>?): List<T>

    val clazz: Class<T>
}