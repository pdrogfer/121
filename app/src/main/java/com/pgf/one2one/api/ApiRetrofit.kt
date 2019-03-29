package com.pgf.one2one.api

import com.pgf.one2one.model.ApiResponseRecipeList
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRetrofit {

    @GET("api")
    fun searchRecipes(@Query("q") recipeQuery: String?): Single<ApiResponseRecipeList>

}