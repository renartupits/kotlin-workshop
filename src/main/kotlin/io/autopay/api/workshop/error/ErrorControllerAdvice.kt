package io.autopay.api.workshop.error

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import java.util.logging.Level
import java.util.logging.Logger


@ControllerAdvice
class ErrorControllerAdvice {

    private val log = Logger.getLogger(ErrorControllerAdvice::class.java.name)

    @ExceptionHandler(SecurityException::class)
    fun handleSecurityException(e: SecurityException): ResponseEntity<ErrorResponse> {
        log.log(Level.WARNING, "WORKSHOP:ERROR:403:Forbidden", e)
        return ResponseEntity(ErrorResponse(HttpStatus.FORBIDDEN.value(), e.message
                ?: ""), getHeaders(), HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        log.log(Level.WARNING, "WORKSHOP:ERROR:400:Bad request", e)
        return ResponseEntity(ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.message
                ?: "Http message not readable"), getHeaders(), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpClientErrorException::class)
    fun handleHttpClientErrorException(e: HttpClientErrorException): ResponseEntity<ErrorResponse> {
        log.log(Level.WARNING, "WORKSHOP:ERROR:400:Bad request", e)
        return ResponseEntity(ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.message
                ?: ""), getHeaders(), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpServerErrorException::class)
    fun handleHttpServerErrorException(e: HttpServerErrorException): ResponseEntity<ErrorResponse> {
        log.log(Level.WARNING, "WORKSHOP:ERROR:500:Internal server error", e)
        val internalErrorResponse = jacksonObjectMapper().readValue<InternalErrorResponse>(e.responseBodyAsString)
        return ResponseEntity(ErrorResponse(internalErrorResponse.errorCode, internalErrorResponse.message), getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(e: Exception): ResponseEntity<ErrorResponse> {
        log.log(Level.SEVERE, "WORKSHOP:ERROR:500:Internal server error", e)
        return ResponseEntity(ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message
                ?: "Internal server error"), getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    private fun getHeaders(): HttpHeaders {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        return headers
    }
}