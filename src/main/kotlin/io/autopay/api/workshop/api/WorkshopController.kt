package io.autopay.api.workshop.api

import io.autopay.api.workshop.dto.WorkshopResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController
@RequestMapping("/workshop")
class WorkshopController {

    companion object {
        private val log = Logger.getLogger(this::class.java.name)
    }

    @GetMapping("/pingpong")
    fun getStatistics(): WorkshopResponse {
        log.info("Get data for ping pong")
        // call service
        return WorkshopResponse("replace with real data")
    }
}