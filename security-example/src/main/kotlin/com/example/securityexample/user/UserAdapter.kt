package com.example.securityexample.user

import org.springframework.stereotype.Component


@Component
class UserAdapter(private val userRepository: UserRepository) : UserPort {
    override fun save(user: User) {
        userRepository.save(user)
    }

    override fun existByEmail(email: String) : Boolean {
        return !userRepository.existsByEmail(email)
    }
}