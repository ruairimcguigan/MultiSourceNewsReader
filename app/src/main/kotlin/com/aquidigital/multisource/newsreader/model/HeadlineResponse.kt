package com.aquidigital.multisource.newsreader.model


class HeadlineResponse {

    lateinit var status: String
    var totalResult = 0
    var articles = listOf<Article>()


}