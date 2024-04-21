package com.example.securityexample.login



import com.example.securityexample.user.infra.UserRepository
import com.example.securityexample.user.port.UserPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

import org.springframework.stereotype.Component

@Component
class UserDetailsServiceImpl() : UserDetailsService {

    private lateinit var userPort: UserPort

    override fun loadUserByUsername(username: String): UserDetails {

        val user = userPort.findByEmail(username).orElseThrow {
            throw UsernameNotFoundException("User not found")
        }

        return StaticUserDetails(user)
    }
}