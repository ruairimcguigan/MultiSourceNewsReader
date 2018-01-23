package com.aquidigital.multisource.newsreader.common.api

import  android.util.Log
import com.aquidigital.multisource.newsreader.BuildConfig.BASE_URL
import com.aquidigital.multisource.newsreader.common.api.Constants.HTTP_TIMEOUT_PERIOD
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

    inline fun <reified T> create(): T {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.readTimeout(HTTP_TIMEOUT_PERIOD,
                TimeUnit.SECONDS).connectTimeout(HTTP_TIMEOUT_PERIOD, TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor({ l -> Log.d("HTTP", l )})
        loggingInterceptor.level = BASIC

        httpClientBuilder.addInterceptor(loggingInterceptor)

        val httpClient = httpClientBuilder.build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(createObjectWrapper()))
                .build()

        return retrofit.create(T::class.java)
    }

     fun createObjectWrapper(): ObjectMapper? {
        val objectMapper = ObjectMapper()
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false)
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, false)
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, false)
        return objectMapper
    }
}