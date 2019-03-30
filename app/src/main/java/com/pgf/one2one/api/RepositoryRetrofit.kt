package com.pgf.one2one.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pgf.one2one.model.ApiResponseRecipeList
import com.pgf.one2one.model.Recipe
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    fun searchRecipes(recipeQuery: String): LiveData<List<Recipe>> {

        var listRecipes = MutableLiveData<List<Recipe>>()

//        apiRetrofit.searchRecipes2(recipeQuery).enqueue(object : Callback<ApiResponseRecipeList> {
//            override fun onFailure(call: Call<ApiResponseRecipeList>, t: Throwable) {
//                Log.i("RepositoryRetrofit", "searchRecipes onError ${t.message}")
//            }
//
//            override fun onResponse(call: Call<ApiResponseRecipeList>, response: Response<ApiResponseRecipeList>) {
//                Log.i("RepositoryRetrofit", "searchRecipes onSuccess ${response.body().toString()}")
//
//                listRecipes.postValue(response.body()?.results as List<Recipe>?)
//            }
//
//        })

        return listRecipes
    }

    fun searchRecipes2(recipeQuery: String): Single<ApiResponseRecipeList> {

        return apiRetrofit.searchRecipes2(recipeQuery)
    }

}
