package com.example.digitalwallet.controllers

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("test")
class TransferControllerTest {

    @Test
    fun `should make a transfer with success`() {
        // Given

        // When

        // Then
    }
}