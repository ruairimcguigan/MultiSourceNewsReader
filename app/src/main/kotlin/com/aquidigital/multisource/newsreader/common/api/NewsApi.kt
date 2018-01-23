package com.aquidigital.multisource.newsreader.common.api

import com.aquidigital.multisource.newsreader.BuildConfig
import com.aquidigital.multisource.newsreader.common.data.model.ArticlesResponse
import com.aquidigital.multisource.newsreader.common.data.model.HeadlineResponse
import com.aquidigital.multisource.newsreader.common.data.model.SourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun getHeadlines(
            @Query("country") country: String?,
            @Query("category") category: String?,
            @Query("q") keyword: String?,
            @Query("apiKey") apiKey: String = BuildConfig.API_KEY)
            : Call<HeadlineResponse>

    @GET("sources")
    fun getSources(
            @Query("category") category: String?,
            @Query("language") language: String?,
            @Query("country") country: String?,
            @Query("apiKey") apiKey: String = BuildConfig.API_KEY)
            : Call<SourceResponse>

    @GET("everything")
    fun getSearchResults(
            @Query("q") query: String?,
            @Query("sortBy") sortBy: String?,
            @Query("language") language: String?,
            @Query("pageSize") pageSize: Int?,
            @Query("page") page: String?,
            @Query("apiKey") apiKey: String = BuildConfig.API_KEY)
            : Call<ArticlesResponse>
}