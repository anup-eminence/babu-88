package com.sona.babu88.api.model.response

import com.google.gson.annotations.SerializedName

data class GetBankingMethodsResponse(

	@field:SerializedName("ok")
	val ok: Any?,

	@field:SerializedName("results")
	val results: List<ResultsItem?>?
)

data class ResultsItem(

	@field:SerializedName("method")
	val method: String?,

	@field:SerializedName("_id")
	val id: String?,

	@field:SerializedName("category")
	val category: String?
)