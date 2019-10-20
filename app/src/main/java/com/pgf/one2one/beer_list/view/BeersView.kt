package com.pgf.one2one.beer_list.view

import com.pgf.one2one.model.Beer

interface BeersView {
    fun updateBeersList(response: List<Beer>)
}