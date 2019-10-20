package com.pgf.one2one.beer_list.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pgf.one2one.R
import com.pgf.one2one.model.Beer
import com.pgf.one2one.beer_list.presenter.BeersPresenter
import com.pgf.one2one.beer_list.view.BeersView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleOwner, BeersView {

    private lateinit var presenter: BeersPresenter
    private val beerAdapter: BeersAdapter = BeersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = BeersPresenter(this)

        initUI()
    }

    private fun initUI() {

        list_recipes.adapter = beerAdapter
        list_recipes.layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        list_recipes.addItemDecoration(decoration)

        search_recipe.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                list_recipes.scrollToPosition(0)
                presenter.getRecipes(s.toString())
            }
        })
    }

    override fun updateBeersList(response: List<Beer>) {
        beerAdapter.setBeers(response)
    }
}
