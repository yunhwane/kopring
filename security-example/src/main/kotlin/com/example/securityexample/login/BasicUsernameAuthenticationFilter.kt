package com.example.securityexample.login

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


class BasicUsernamePasswordAuthenticationFilter : AbstractAuthenticationProcessingFilter(DEFAULT_LOGIN_REQUEST_URL) {
    companion object {
        private val logger = LoggerFactory.getLogger(BasicUsernamePasswordAuthenticationFilter::class.java)
        private const val DEFAULT_LOGIN_REQUEST_URL = "/api/login"
        private const val HTTP_METHOD = "POST"
    }

    private val objectMapper: ObjectMapper = ObjectMapper()

    init {
        setFilterProcessesUrl(DEFAULT_LOGIN_REQUEST_URL)
        setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher(DEFAULT_LOGIN_REQUEST_URL, HTTP_METHOD))
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val loginRequest = objectMapper.readValue(request?.inputStream, LoginRequest::class.java)
        val username = loginRequest.username
        val password = loginRequest.password


        logger.info("username: $username, password: $password")
        val authenticationToken = UsernamePasswordAuthenticationToken(username, password)
        return authenticationManager.authenticate(authenticationToken)
    }
}