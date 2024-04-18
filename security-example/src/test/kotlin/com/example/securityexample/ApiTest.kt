package com.example.securityexample

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import java.util.logging.Logger


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiTest {
    @LocalServerPort
    private var port: Int = 0

    @BeforeEach
    fun setup() {
        if(RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port
        }
    }
}