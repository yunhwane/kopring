package com.example.securityexample.security

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
@SpringBootTest
class PasswordEncodeTest (
        @Autowired private val passwordEncoder: PasswordEncoder
){
    @Test
    fun `password encoding test`() {
        //given
        val initPassword = "initpassword"

        //when
        val encodePassword = passwordEncoder.encode(initPassword)

        //then
        assertAll({ assertNotEquals(initPassword, encodePassword) },
        { assertTrue(passwordEncoder.matches(initPassword, encodePassword))})
    }
}