package com.aquidigital.multisource.newsreader.common.data.model


class HeadlineResponse {

    lateinit var status: String
    var totalResults: Int? = 0
    var articles = listOf<Article>()


}