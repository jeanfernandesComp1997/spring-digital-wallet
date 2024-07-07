package com.example.digitalwalletevents

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class DigitalWalletEventsApplication

fun main(args: Array<String>) {
	runApplication<DigitalWalletEventsApplication>(*args)
}
