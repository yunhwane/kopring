package com.example.securityexample.user.service

import com.example.securityexample.user.domain.User
import com.example.securityexample.user.infra.UserRepository
import com.example.securityexample.user.port.UserPort
import org.springframework.stereotype.Component
import java.util.*


@Component
class UserAdapter(private val userRepository: UserRepository) : UserPort {
    override fun save(user: User) {
        userRepository.save(user)
    }

    override fun existByEmail(email: String) : Boolean {
        return !userRepository.existsByEmail(email)
    }

    override fun findByEmail(email: String): Optional<User> {
        return userRepository.findByEmail(email)
    }
}