package com.pgf.one2one.beer_list.presenter

import android.util.Log
import com.pgf.one2one.api.ApiRetrofit
import com.pgf.one2one.model.Beer
import com.pgf.one2one.beer_list.view.BeersView
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BeersPresenter(private var beersView: BeersView) {

    fun getRecipes(searchTerm: String) {

        val searchResults = ApiRetrofit.instance.searchBeers(searchTerm)
        searchResults
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Beer>> {
                override fun onSuccess(response: List<Beer>) {
                    Log.i("MainActivity", "onSuccess: $response")

                    beersView.updateBeersList(response)
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
