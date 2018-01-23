package com.aquidigital.multisource.newsreader.common.data.model

import com.aquidigital.multisource.newsreader.common.api.DateToLongDeserializer
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Article : RealmObject(){

    @PrimaryKey
    lateinit var url: String
    lateinit var title: String
    lateinit var source: Source
    var author: String? = null
    var description: String? = null
    var urlToImage: String? = null
    var category: String? = null
    @JsonDeserialize(using = DateToLongDeserializer::class)
    var publishedAt: Long? = null

    @JsonIgnore
    var isUnread = true
}