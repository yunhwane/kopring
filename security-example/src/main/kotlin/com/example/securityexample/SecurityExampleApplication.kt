package com.example.securityexample

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SecurityExampleApplication


inline fun <reified T> T.logger() = LoggerFactory.getLogger(T::class.java)!!


fun main(args: Array<String>) {
    runApplication<SecurityExampleApplication>(*args)
}
