package com.example.securityexample.login

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response

object LoginSteps {

    fun `로그인 요청`(request: LoginRequest) : ExtractableResponse<Response> {
        return RestAssured.given().log().all()
            .contentType("application/json")
            .body(request)
            .post("/api/login")
            .then().log().all()
            .extract()
    }

    fun `로그인 요청 성공 모델 생성`() : LoginRequest {
        return LoginRequest("testUser", "testPassword")
    }

    fun `로그인 요청 실패 모델 생성`() : LoginRequest {
        return LoginRequest("testUser", "failPassword")
    }


}