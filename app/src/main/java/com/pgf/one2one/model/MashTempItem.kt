package com.pgf.one2one.model

import com.google.gson.annotations.SerializedName

data class MashTempItem(

	@field:SerializedName("duration")
	val duration: Int? = null,

	@field:SerializedName("temp")
	val temp: Temp? = null
)