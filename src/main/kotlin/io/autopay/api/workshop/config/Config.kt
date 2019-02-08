package io.autopay.api.workshop.config

import io.autopay.api.workshop.error.ErrorHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class Config {

    @Bean
    fun restTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        restTemplate.errorHandler = ErrorHandler()
        return restTemplate
    }
}