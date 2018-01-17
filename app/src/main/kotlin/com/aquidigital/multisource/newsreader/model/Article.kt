package com.aquidigital.multisource.newsreader.model

import com.aquidigital.multisource.newsreader.util.DateToLongDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Article : RealmObject(){

    @PrimaryKey
    lateinit var  url: String
    lateinit var title: String

    lateinit var source: Source
    lateinit var author: String
    lateinit var description: String
    lateinit var urlToImage: String
    lateinit var category: String

    @JsonDeserialize(using = DateToLongDeserializer::class) // not supported in realm
    var publishedAt: Long? = null
}