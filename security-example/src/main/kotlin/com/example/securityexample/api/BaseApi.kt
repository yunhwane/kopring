package com.example.securityexample.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class BaseApi {

        @RequestMapping("/health-check")
        fun getHello(): String {
            return "hello world!"
        }

}