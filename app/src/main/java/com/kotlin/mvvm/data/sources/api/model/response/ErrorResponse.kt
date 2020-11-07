package com.kotlin.mvvm.data.sources.api.model.response

data class ErrorResponse(val statusCode: Int, val error: String, val message: String)