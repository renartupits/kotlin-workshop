package io.autopay.api.workshop.config

import com.google.common.base.Predicates
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.DocExpansion
import springfox.documentation.swagger.web.UiConfiguration
import springfox.documentation.swagger.web.UiConfigurationBuilder
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig(@Autowired private val env: Environment) {

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .produces(setOf("application/json"))
            .consumes(setOf("application/json"))
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(Predicates.not(PathSelectors.regex("/error")))
            .build()

    @Bean
    fun uiConfiguration(): UiConfiguration {
        return UiConfigurationBuilder.builder()
                .docExpansion(DocExpansion.LIST)
                .defaultModelsExpandDepth(2)
                .displayRequestDuration(false)
                .supportedSubmitMethods(emptyArray())
                .build()
    }

    fun apiInfo(): ApiInfo {
        val title = "Kotlin workshop"
        val swaggerApiVersion = env.getProperty("application.version")

        return ApiInfoBuilder()
                .title(title)
                .version(swaggerApiVersion)
                .build()
    }
}