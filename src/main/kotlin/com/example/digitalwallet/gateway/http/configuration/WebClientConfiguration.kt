package com.example.digitalwallet.gateway.http.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class WebClientConfiguration {

    @Bean
    fun defaultRestClient(): RestClient {
        return RestClient
            .builder()
            .build()
    }
}