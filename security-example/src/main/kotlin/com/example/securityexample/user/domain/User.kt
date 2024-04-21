package com.example.securityexample.user.domain

import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder

@Entity(name = "users")
data class User(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                var id: Long? = null,
                var email : String,
                var password: String,
                @Enumerated(EnumType.STRING)
                var role: Role) {
    constructor(email: String, password: String) : this(
            id = null,
            email = email,
            password = password,
            role = Role.USER
    )

    fun encodePassword(passwordEncoder: PasswordEncoder) {
        password = passwordEncoder.encode(password)
    }
    fun matchPassword(passwordEncoder: PasswordEncoder, checkPassword: String?): Boolean {
        return passwordEncoder.matches(checkPassword, password)
    }
}