package com.pgf.one2one.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import com.pgf.one2one.R
import com.pgf.one2one.api.RepositoryRetrofit
import com.pgf.one2one.model.ApiResponseRecipeList
import com.pgf.one2one.model.Recipe
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ActivityRecipesViewModel
    private val recipesAdapter: RecipesAdapter = RecipesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {

        list_recipes.adapter = recipesAdapter
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

        val searchResults = RepositoryRetrofit.instance.searchRecipes(searchTerm)
        searchResults.subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ApiResponseRecipeList> {
                override fun onSuccess(response: ApiResponseRecipeList) {
                    Log.i("MainActivity", "onSuccess: $response")

                    recipesAdapter.setRecipes(response.results as ArrayList<Recipe>)
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
