package com.example.digitalwalletevents

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableRabbit
class DigitalWalletEventsApplication

fun main(args: Array<String>) {
	runApplication<DigitalWalletEventsApplication>(*args)
}
