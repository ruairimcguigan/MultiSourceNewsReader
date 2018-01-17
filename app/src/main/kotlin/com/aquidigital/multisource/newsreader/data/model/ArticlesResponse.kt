package com.aquidigital.multisource.newsreader.data.model

class ArticlesResponse{

    lateinit var status: String
    var totalResults = 0
    var articles = listOf<Article>()
    lateinit var sortBy: String

}