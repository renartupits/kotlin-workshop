package io.autopay.api.workshop.service

import io.autopay.api.workshop.dto.PingpongResponse
import io.autopay.api.workshop.dto.WorkshopResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class WorkshopService (@Autowired private val restTemplate: RestTemplate){

    fun getStatistics(): WorkshopResponse {
        val pingpongStatistics = getPingpongStatistics()

        return WorkshopResponse(message = "replace with real data")
    }

    private fun getPingpongStatistics(): PingpongResponse {
        val response = restTemplate.exchange(
                "https://us-central1-autopay-test-api.cloudfunctions.net/pingpongApi",
                HttpMethod.GET,
                HttpEntity<HttpHeaders>(HttpHeaders()),
                PingpongResponse::class.java
        )
        return response.body!!
    }

}