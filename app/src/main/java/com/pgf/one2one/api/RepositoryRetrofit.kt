package com.pgf.one2one.api

import com.pgf.one2one.model.ApiResponseRecipeList
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryRetrofit private constructor() {

    internal var interceptor: HttpLoggingInterceptor
    internal var client: OkHttpClient

    internal var retrofitInstance: Retrofit
    internal var apiRetrofit: ApiRetrofit

    init {

        interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        retrofitInstance = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        apiRetrofit = retrofitInstance.create(ApiRetrofit::class.java)
    }

    companion object {

        private val BASE_URL = "http://www.recipepuppy.com/"

        private var repositoryRetrofitInstance: RepositoryRetrofit? = null

        val instance: RepositoryRetrofit
            get() {

                if (repositoryRetrofitInstance == null) {
                    repositoryRetrofitInstance = RepositoryRetrofit()
                }
                return repositoryRetrofitInstance as RepositoryRetrofit
            }
    }

    fun searchRecipes(recipeQuery: String): Single<ApiResponseRecipeList> {
        return apiRetrofit.searchRecipes(recipeQuery)
    }

}
