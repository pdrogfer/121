package com.pgf.one2one.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pgf.one2one.R
import com.pgf.one2one.api.RepositoryRetrofit
import com.pgf.one2one.model.ApiResponseRecipeList
import com.pgf.one2one.model.Recipe
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var viewModel: ActivityRecipesViewModel
    private val recipesAdapter: RecipesAdapter = RecipesAdapter(ArrayList<Recipe>())

    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()

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
//                getRecipes(s.toString())
                getRecipes2(s.toString())
            }

        })
    }

    private fun getRecipes1(searchTerm: String) {

        val searchResults = RepositoryRetrofit.instance.searchRecipes(searchTerm)
        searchResults.observe(this, Observer<List<Recipe>> {
            recipesAdapter.setRecipes(it)
        })
    }

    private fun getRecipes2(searchTerm: String) {

        val searchResults = RepositoryRetrofit.instance.searchRecipes2(searchTerm)
        searchResults.subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ApiResponseRecipeList> {
                override fun onSuccess(response: ApiResponseRecipeList) {
                    Log.i("MainActivity", "onSuccess: $response")

                    val handler = Handler(Looper.getMainLooper())
                    handler.post {
                        updateAdapter(response)
                    }
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
