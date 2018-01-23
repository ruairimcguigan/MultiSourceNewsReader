package com.aquidigital.multisource.newsreader.common.data.storage

import com.aquidigital.multisource.newsreader.common.extensions.inTransactionAsync
import com.aquidigital.multisource.newsreader.common.extensions.loadAsync
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmQuery
import io.realm.Sort


abstract class RepositoryBase<T> : Repository<T> where T : RealmModel {

    protected val realm: Realm = Realm.getDefaultInstance();

    abstract protected val primaryKey: String


    override fun getById(id: String): T? {
        return realm.where(clazz)
                .equalTo(primaryKey, id)
                .findFirst()
        // add more filters as is needed
    }

    override fun add(item: T) {
        return realm.executeTransaction { r -> r.copyToRealm(item)
        }
    }

    override suspend fun addAll(items: List<T>) =
            realm.inTransactionAsync { copyToRealmOrUpdate(items) }

    suspend override fun delete(filter: RealmQuery<T>.() -> Unit) {
        return realm.inTransactionAsync {
            val results = where(clazz)
            filter(results)
            results.findAll().deleteAllFromRealm()
        }
    }

    suspend override fun deleteAll() = realm.inTransactionAsync { delete(clazz) }

    override fun update(id: String, modifier: T.() -> Unit) {
        realm.executeTransaction {
            r -> val dbItem = r.where(clazz)
                .equalTo(primaryKey, id)
                .findFirst()

            modifier(dbItem)
        }
    }

    override suspend fun updateAsync(vararg ids: String, modifier: T.() -> Unit) {
        realm.inTransactionAsync {
            ids.map { id ->
                where(clazz)
                        .equalTo(primaryKey, id)
                        .findFirst()
            }.forEach { item -> modifier(item) }
        }
    }

    override fun count(): Long = realm.where(clazz).count()

    override fun count(filter: RealmQuery<T>.() -> Unit): Long {
        val results = realm.where(clazz)
        filter(results)
        return results.count()
    }

    override suspend fun query(filter: RealmQuery<T>.() -> Unit, sortFields: Array<String>?, orders: Array<Sort>?): List<T> {
        val results = realm.where(clazz)
        filter(results)
        return if (sortFields == null) {
            results.findAllAsync()
                    .loadAsync()
        } else {
            results.findAllSortedAsync(sortFields, orders)
                    .loadAsync()
        }
    }

    override fun close() {
        realm.close()
    }
}