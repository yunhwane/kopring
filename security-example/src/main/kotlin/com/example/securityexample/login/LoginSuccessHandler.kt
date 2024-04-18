package com.example.securityexample.login

import com.example.securityexample.logger
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler

class LoginSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {

        val username = authentication.name
        logger().info("User $username has logged in")

        response.status = HttpServletResponse.SC_OK
        response.contentType = "application/json"
        response.writer.write("{\"message\": \"Login successful\"}")


    }
}