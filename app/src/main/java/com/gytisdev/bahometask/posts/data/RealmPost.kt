package com.gytisdev.bahometask.posts.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmPost(): RealmObject() {

    @PrimaryKey
    var id: Int = 0
    var title: String = ""
}