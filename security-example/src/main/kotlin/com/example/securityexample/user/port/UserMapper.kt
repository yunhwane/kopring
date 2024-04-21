package com.example.securityexample.user.port

import com.example.securityexample.user.service.UserRequest
import com.example.securityexample.user.domain.User

class UserMapper {
    companion object {
        fun createUserInstance(userRequest: UserRequest): User {
            return User(userRequest.email, userRequest.password)
        }
    }
}