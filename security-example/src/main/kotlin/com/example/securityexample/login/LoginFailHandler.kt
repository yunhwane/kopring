package com.example.securityexample.login

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler

class LoginFailHandler : SimpleUrlAuthenticationFailureHandler() {
    init {
        isUseForward = true
        setDefaultFailureUrl("/login?error")
    }
}