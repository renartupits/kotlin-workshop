package io.autopay.api.workshop.error

data class InternalErrorResponse(
        val success: Boolean,
        val message: String,
        val errorCode: Int,
        val parameterName: String?
)