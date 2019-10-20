package com.pgf.one2one.model

import com.google.gson.annotations.SerializedName

data class BoilVolume(

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)