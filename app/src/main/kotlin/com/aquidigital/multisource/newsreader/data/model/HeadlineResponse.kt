package com.aquidigital.multisource.newsreader.data.model


class HeadlineResponse {

    lateinit var status: String
    var totalResult = 0
    var articles = listOf<Article>()


}