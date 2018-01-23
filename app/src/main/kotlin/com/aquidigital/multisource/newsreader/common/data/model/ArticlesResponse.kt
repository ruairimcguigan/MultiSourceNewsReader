package com.aquidigital.multisource.newsreader.common.data.model

class ArticlesResponse{

    lateinit var status: String
    lateinit var source: Source
    var totalResults: Int = 0
    lateinit var sortBy: String
    lateinit var articles: List<Article>
}