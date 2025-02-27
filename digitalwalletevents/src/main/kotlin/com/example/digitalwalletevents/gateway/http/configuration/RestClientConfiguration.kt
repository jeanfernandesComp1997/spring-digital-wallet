package com.example.digitalwalletevents.gateway.http.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfiguration {

    @Bean
    fun defaultRestClient(): RestClient {
        return RestClient
            .builder()
            .build()
    }
}