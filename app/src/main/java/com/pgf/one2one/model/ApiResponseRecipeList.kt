package com.pgf.one2one.model

import com.google.gson.annotations.SerializedName

data class ApiResponseRecipeList(

	@field:SerializedName("href")
	val href: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("version")
	val version: Double? = null,

	@field:SerializedName("results")
	val results: List<Recipe?>? = null
)