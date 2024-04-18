package com.example.securityexample.login



import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

import org.springframework.stereotype.Component

@Component
class UserDetailsServiceImpl() : UserDetailsService {

    @Value("\${app.user.password}")
    private lateinit var password: String

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null || username != "testUser") {
            throw UsernameNotFoundException("User not found")
        }

        return StaticUserDetails(password)
    }
}