package com.pgf.one2one.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pgf.one2one.R
import com.pgf.one2one.model.Recipe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_item.view.*

class RecipesAdapter(var recipesList: ArrayList<Recipe>) : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    fun setRecipes(recipes: List<Recipe>) {
        recipesList = recipes as ArrayList<Recipe>
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return recipesList.size
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipe = recipesList.get(position)

        if (recipe.thumbnail != null && recipe.thumbnail.isNotEmpty()) {
            Picasso.get()
                .load(recipe.thumbnail)
                .error(R.color.switch_thumb_disabled_material_light)
                .resize(50, 50)
                .centerCrop()
                .into(holder.thumbnail)
        }
        holder.title.text = recipe.title
        holder.ingredients.text = recipe.ingredients
    }


    class RecipesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val thumbnail = view.recipe_thumbnail
        val title = view.recipe_title
        val ingredients = view.recipe_ingredients
    }

}
