package com.example.securityexample.login

import com.example.securityexample.ApiTest
import org.apache.http.HttpStatus
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.absoluteValue

class LoginApiTest : ApiTest() {

    @Test
    fun `login with correct credentials`() {
        val request = LoginSteps.`로그인 요청 성공 모델 생성`()
        val response = LoginSteps.`로그인 요청`(request)
        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK.absoluteValue)
    }

    @Test
    fun `login with fail credentials`() {
        val request = LoginSteps.`로그인 요청 실패 모델 생성`()
        val response = LoginSteps.`로그인 요청`(request)
        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_FORBIDDEN.absoluteValue)
    }
}