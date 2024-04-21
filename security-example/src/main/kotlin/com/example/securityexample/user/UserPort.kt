package com.example.securityexample.user

interface UserPort {
   fun save(user: User)
   fun existByEmail(email: String) : Boolean
}