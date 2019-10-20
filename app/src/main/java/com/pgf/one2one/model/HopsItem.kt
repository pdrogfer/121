package com.pgf.one2one.model

import com.google.gson.annotations.SerializedName

data class HopsItem(

	@field:SerializedName("add")
	val add: String? = null,

	@field:SerializedName("amount")
	val amount: Amount? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("attribute")
	val attribute: String? = null
)