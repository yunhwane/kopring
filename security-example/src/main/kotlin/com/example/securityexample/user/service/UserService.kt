package com.example.securityexample.user.service

import com.example.securityexample.user.port.UserMapper.Companion.createUserInstance
import com.example.securityexample.user.port.UserPort
import com.example.securityexample.user.service.UserRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI


@RestController
@RequestMapping("/api/users")
class UserService constructor(private val userPort: UserPort, private val passwordEncoder: PasswordEncoder){

    @PostMapping
    fun addUser(@RequestBody userRequest: UserRequest) : ResponseEntity<Any> {
        verifyDuplicatedEmail(userRequest.email);
        val user = createUserInstance(userRequest);
        user.encodePassword(passwordEncoder)
        userPort.save(user);

        val nextUrl = URI.create("/api/users/me")
        return ResponseEntity.created(nextUrl).build();
    }

    private fun verifyDuplicatedEmail(email : String) {
        require(userPort.existByEmail(email))
    }
}






