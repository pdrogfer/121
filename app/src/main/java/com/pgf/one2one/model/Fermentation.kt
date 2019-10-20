package com.pgf.one2one.model

import com.google.gson.annotations.SerializedName

data class Fermentation(

	@field:SerializedName("temp")
	val temp: Temp? = null
)