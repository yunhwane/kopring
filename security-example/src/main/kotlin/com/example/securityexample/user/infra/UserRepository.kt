package com.example.securityexample.user.infra

import com.example.securityexample.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.util.Optional


@Component
interface UserRepository : JpaRepository<User, Long> {
    fun existsByEmail(email:String) : Boolean
    fun findByEmail(email:String) : Optional<User>
}