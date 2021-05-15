package com.gytisdev.bahometask.application.base

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmQuery
import io.realm.RealmResults

abstract class BaseRealmRepository<T : RealmModel>(protected val realm: Realm) {

    fun findAll(): RealmResults<T> = queryBuilder().findAll()

    fun find(id: Long): T? = queryBuilder().equalTo("id", id).findFirst()
    fun find(id: Int): T? = queryBuilder().equalTo("id", id).findFirst()
    fun find(id: String): T? = queryBuilder().equalTo("id", id).findFirst()

    fun insert(entity: T) = realm.executeTransaction { it }

    protected fun queryBuilder(): RealmQuery<T> = realm.where(getPersistentType())

    protected abstract fun getPersistentType(): Class<T>
}