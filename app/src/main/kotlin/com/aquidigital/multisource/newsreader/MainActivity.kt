package com.aquidigital.multisource.newsreader

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.aquidigital.multisource.newsreader.api.NewsApi
import com.aquidigital.multisource.newsreader.data.model.HeadlineResponse
import com.aquidigital.multisource.newsreader.data.model.SourceResponse
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchButton.setOnClickListener({ executeSearch() })
    }


    // todo for testing api calls - remove
    private fun executeSearch() {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor({ l -> Log.d("HTTP", l) })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        httpClientBuilder.addInterceptor(loggingInterceptor)

        val httpClient = httpClientBuilder.build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient)
                .build()

        val userClient = retrofit.create(NewsApi::class.java)

        val call: Call<SourceResponse> = userClient.getSources(
                "business",
                "de",
                "de"

        )
        call.enqueue(object : Callback<SourceResponse> {
            override fun onResponse(call: Call<SourceResponse>?,
                                    response: Response<SourceResponse>?) {

                if (response != null && response.isSuccessful) {
                    outputView.text = response.body()?.sources?.get(1)?.description
                }
            }
            override fun onFailure(call: Call<SourceResponse>?, t: Throwable?) {
                toast("fail")
            }
        })
    }
}


