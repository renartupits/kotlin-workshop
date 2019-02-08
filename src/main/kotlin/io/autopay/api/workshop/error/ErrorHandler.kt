package io.autopay.api.workshop.error

import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.DefaultResponseErrorHandler
import org.springframework.web.client.HttpServerErrorException

class ErrorHandler : DefaultResponseErrorHandler() {

    override fun handleError(response: ClientHttpResponse) {
        throw HttpServerErrorException(response.statusCode, response.statusText,
                response.headers, getResponseBody(response), getCharset(response))
    }
}