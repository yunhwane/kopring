package com.example.securityexample.user

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response

object SignUpSteps {


    fun `회원 가입 요청`(request: UserRequest) : ExtractableResponse<Response> {
        return RestAssured.given().log().all()
                .contentType("application/json")
                .body(request)
                .post("/api/users")
                .then().log().all()
                .extract()
    }

    fun `회원가입 요청 성공 모델 생성`() : UserRequest {
        return UserRequest("testUser", "test")
    }
}