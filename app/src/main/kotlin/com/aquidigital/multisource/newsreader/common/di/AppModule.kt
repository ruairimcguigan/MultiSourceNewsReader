package com.aquidigital.multisource.newsreader.common.di

import android.content.Context
import com.aquidigital.multisource.newsreader.common.api.ApiFactory
import com.aquidigital.multisource.newsreader.common.api.Constants.SHARED_PREFS
import com.aquidigital.multisource.newsreader.common.api.NewsApi
import com.aquidigital.multisource.newsreader.common.data.model.Article
import com.aquidigital.multisource.newsreader.common.data.model.Source
import com.aquidigital.multisource.newsreader.common.data.storage.Repository
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun providesSharedPref() = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

    @Provides
    fun providesNewsApi() = ApiFactory.create<NewsApi>()

    @Provides
    fun providesSourcesRepo(): Repository<Source> = SourceRepository()

    @Provides
    fun providesHeadlinesRepo(): Repository<Headli> = HeadlinesRepository()

    @Provides
    fun providesSearchResultsRepo(): Repository<Article> = SearchResultsRepository()



}