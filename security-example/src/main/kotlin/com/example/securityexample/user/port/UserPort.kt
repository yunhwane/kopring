package com.example.securityexample.user.port

import com.example.securityexample.user.domain.User
import java.util.Optional

interface UserPort {
   fun save(user: User)
   fun existByEmail(email: String) : Boolean
   fun findByEmail(email: String) : Optional<User>
}