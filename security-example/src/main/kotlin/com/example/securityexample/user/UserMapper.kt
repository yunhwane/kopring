package com.example.securityexample.user

class UserMapper {
    companion object {
        fun createUserInstance(userRequest: UserRequest): User {
            return User(userRequest.email, userRequest.password)
        }
    }
}