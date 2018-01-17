package com.aquidigital.multisource.newsreader.model

class ArticlesResponse{

    lateinit var status: String
    var totalResults = 0
    var articles = listOf<Article>()
    lateinit var sortBy: String

}