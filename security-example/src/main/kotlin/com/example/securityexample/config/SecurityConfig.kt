package com.example.securityexample.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http.csrf{
            it.disable()
        }

        http.authorizeHttpRequests {
            it.requestMatchers("/api/health-check").permitAll()
            it.anyRequest().authenticated()
        }

        http.sessionManagement{
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        http.formLogin {
            it.disable()
        }

        return http.build();
    }
}