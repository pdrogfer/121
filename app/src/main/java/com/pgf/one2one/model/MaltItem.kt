package com.pgf.one2one.model

import com.google.gson.annotations.SerializedName

data class MaltItem(

	@field:SerializedName("amount")
	val amount: Amount? = null,

	@field:SerializedName("name")
	val name: String? = null
)