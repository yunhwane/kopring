package com.example.securityexample.config

import com.example.securityexample.logger
import com.example.securityexample.login.BasicUsernamePasswordAuthenticationFilter
import com.example.securityexample.login.LoginFailHandler
import com.example.securityexample.login.LoginSuccessHandler
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.context.SecurityContextHolderFilter



@Configuration
class SecurityConfig (private val userDetailsService: UserDetailsService) {



    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http.csrf{
            it.disable()
        }

        http.authorizeHttpRequests {
            it.requestMatchers("/api/health-check", "/api/users").permitAll()
            it.anyRequest().authenticated()
        }

        http.sessionManagement{
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        http.formLogin {
            it.disable()
        }

        http.httpBasic {
            it.disable()
        }

        http.addFilterBefore(LoggingFilter(), SecurityContextHolderFilter::class.java)
        http.addFilterBefore(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)

        return http.build();
    }

    @Bean
    fun authenticationManager() : AuthenticationManager {
        DaoAuthenticationProvider().apply {
            setPasswordEncoder(bcryptPasswordEncoder())
            setUserDetailsService(userDetailsService)
            return ProviderManager(this)
        }
    }

    @Bean
    fun bcryptPasswordEncoder() : PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun customUsernamePasswordAuthenticationFilter() : BasicUsernamePasswordAuthenticationFilter {
        BasicUsernamePasswordAuthenticationFilter().apply {
            setAuthenticationSuccessHandler(LoginSuccessHandler())
            setAuthenticationFailureHandler(LoginFailHandler())
            setAuthenticationManager(authenticationManager())
            return this
        }
    }
}



class LoggingFilter : Filter {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        val req = request as HttpServletRequest
        val res = response as HttpServletResponse

        // 현재 사용자 정보 가져오기
        val authentication = SecurityContextHolder.getContext().authentication

        // 사용자 정보가 있다면 로그에 포함시키기
        val userInfo = if (authentication != null && authentication.isAuthenticated) {
            "User: ${authentication.name}, Roles: ${authentication.authorities.joinToString(", ")}"
        } else {
            "Anonymous User"
        }


        val path = req.requestURI
        val method = req.method

        val startTime = System.currentTimeMillis()
        chain?.doFilter(request, response)
        val endTime = System.currentTimeMillis()

        val log = "[${method}] ${path} (${endTime - startTime}ms), $userInfo"

        logger().info(log)
    }
}