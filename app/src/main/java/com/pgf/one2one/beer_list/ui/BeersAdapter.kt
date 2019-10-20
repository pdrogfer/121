package com.pgf.one2one.beer_list.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pgf.one2one.R
import com.pgf.one2one.model.Beer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_item.view.*

class BeersAdapter() : RecyclerView.Adapter<BeersAdapter.BeerItemViewHolder>() {


    private var beersList: List<Beer> = emptyList()

    fun setBeers(beers: List<Beer>) {
        beersList = beers
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerItemViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return BeerItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return beersList?.size
    }

    override fun onBindViewHolder(holder: BeerItemViewHolder, position: Int) {
        val beer = beersList[position]

        if (beer.imageUrl != null && beer.imageUrl.isNotEmpty()) {
            Picasso.get()
                .load(beer.imageUrl)
                .error(R.drawable.ic_bottle_wine)
                .resize(50, 50)
                .centerCrop()
                .into(holder.thumbnail)
        }
        holder.name.text = beer.name
        holder.description.text = beer.description
    }


    class BeerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val thumbnail = view.beer_thumbnail
        val name = view.beer_name
        val description = view.beer_description
    }

}
