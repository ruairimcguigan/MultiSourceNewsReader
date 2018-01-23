package com.aquidigital.multisource.newsreader.common.data.storage

import com.aquidigital.multisource.newsreader.common.data.model.Source

class SourcesRepository: RepositoryBase<Source>() {

    override val primaryKey: String
        get() = "id"
    override val clazz: Class<Source>
        get() = Source::class.java
}