package com.example.securityexample.user

import com.example.securityexample.ApiTest
import com.example.securityexample.DatabaseCleanup
import io.restassured.RestAssured
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest



class UserApiTest : ApiTest() {


    @Test
    fun `유저가 회원가입을 진행한다` () {
        val request = SignUpSteps.`회원가입 요청 성공 모델 생성`()
        val response = SignUpSteps.`회원 가입 요청`(request)

        assertThat(response.statusCode()).isEqualTo(201)
    }
    
}