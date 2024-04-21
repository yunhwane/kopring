package com.example.securityexample.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component


@Component
interface UserRepository : JpaRepository<User, Long> {
    fun existsByEmail(email:String) : Boolean
}