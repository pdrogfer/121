package com.pgf.one2one.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pgf.one2one.R
import com.pgf.one2one.api.ApiRetrofit
import com.pgf.one2one.model.ApiResponseRecipeList
import com.pgf.one2one.model.Recipe
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private val recipesAdapter: RecipesAdapter = RecipesAdapter(ArrayList<Recipe>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {

        list_recipes.adapter = recipesAdapter
        list_recipes.layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        list_recipes.addItemDecoration(decoration)

        search_recipe.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                list_recipes.scrollToPosition(0)
                getRecipes(s.toString())
            }
        })
    }

    private fun getRecipes(searchTerm: String) {

        val searchResults = ApiRetrofit.instance.searchRecipes(searchTerm)
        searchResults
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ApiResponseRecipeList> {
                override fun onSuccess(response: ApiResponseRecipeList) {
                    Log.i("MainActivity", "onSuccess: $response")

                    updateAdapter(response)
                }

                override fun onSubscribe(disposable: Disposable) {
                    Log.i("MainActivity", "onSubscribe")
                }

                override fun onError(e: Throwable) {
                    Log.i("MainActivity", "onError $e")
                }
            })
    }

    private fun updateAdapter(response: ApiResponseRecipeList) {
        recipesAdapter.setRecipes(response.results as ArrayList<Recipe>)
    }
}
