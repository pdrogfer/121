package com.pgf.one2one.api

import com.pgf.one2one.model.Beer
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRetrofit {

    companion object {

        private const val BASE_URL = "https://api.punkapi.com/v2/"

        lateinit var instance: ApiRetrofit
            private set

        init {
            create()
        }

        fun create() {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiRetrofit::class.java)
        }

    }

    @GET("beers")
    fun searchBeers(@Query("beer_name") beersQuery: String?): Single<List<Beer>>
}