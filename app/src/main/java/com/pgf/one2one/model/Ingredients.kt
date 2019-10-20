package com.pgf.one2one.model

import com.google.gson.annotations.SerializedName

data class Ingredients(

	@field:SerializedName("hops")
	val hops: List<HopsItem?>? = null,

	@field:SerializedName("yeast")
	val yeast: String? = null,

	@field:SerializedName("malt")
	val malt: List<MaltItem?>? = null
)