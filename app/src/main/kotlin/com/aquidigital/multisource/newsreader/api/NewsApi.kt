package com.aquidigital.multisource.newsreader.api

import com.aquidigital.multisource.newsreader.BuildConfig
import com.aquidigital.multisource.newsreader.data.model.HeadlineResponse
import com.aquidigital.multisource.newsreader.data.model.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface NewsApi {

    @GET("top-headlines")
    fun getHeadlines(
            @Query("country") country: String?,
            @Query("category") category: String?,
            @Query("q") keyword: String?,
            @Query("apiKey") apiKey: String = BuildConfig.NEWS_READER_API_KEY): Call<HeadlineResponse>

    @GET("sources")
    fun getSources(
            @Query("category") category: String?,
            @Query("language") language: String?,
            @Query("country") country: String?,
            @Query("apiKey") apiKey: String = BuildConfig.NEWS_READER_API_KEY): Call<SourceResponse>



}