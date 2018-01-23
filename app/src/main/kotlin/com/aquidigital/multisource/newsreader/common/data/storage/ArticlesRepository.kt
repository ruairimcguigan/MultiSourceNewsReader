package com.aquidigital.multisource.newsreader.common.data.storage

import com.aquidigital.multisource.newsreader.common.data.model.Article

/**
 * Created by aquidigitalltd on 23/01/2018.
 */
class ArticlesRepository: RepositoryBase<Article>() {

    override val primaryKey: String
        get() = "url"
    override val clazz: Class<Article>
        get() = Article::class.java
}