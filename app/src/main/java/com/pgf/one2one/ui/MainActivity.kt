package com.pgf.one2one.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pgf.one2one.R
import com.pgf.one2one.api.RepositoryRetrofit
import com.pgf.one2one.model.ApiResponseRecipeList
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getRecipes()
    }

    private fun getRecipes() {

        val searchResults = RepositoryRetrofit.instance.searchRecipes("pasta")
        searchResults.subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ApiResponseRecipeList> {
                override fun onSuccess(response: ApiResponseRecipeList) {
                    Log.i("MainActivity", "onSuccess: $response")
                }

                override fun onSubscribe(disposable: Disposable) {
                    Log.i("MainActivity", "onSubscribe")
                }

                override fun onError(e: Throwable) {
                    Log.i("MainActivity", "onError $e")
                }

            })
    }
}
