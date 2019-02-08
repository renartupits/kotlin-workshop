package io.autopay.api.workshop

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication(exclude = [(DataSourceAutoConfiguration::class)])
class WorkshopApplicationInitializer : SpringBootServletInitializer() {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(WorkshopApplicationInitializer::class.java, *args)
        }
    }
}

