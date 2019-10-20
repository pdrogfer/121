package com.pgf.one2one.model

import com.google.gson.annotations.SerializedName

data class Method(

	@field:SerializedName("mash_temp")
	val mashTemp: List<MashTempItem?>? = null,

	@field:SerializedName("fermentation")
	val fermentation: Fermentation? = null,

	@field:SerializedName("twist")
	val twist: String? = null
)