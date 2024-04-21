package com.example.securityexample.login
import com.example.securityexample.user.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class StaticUserDetails(private val user: User) : UserDetails {

    private val authorities: Collection<GrantedAuthority> = listOf(SimpleGrantedAuthority("ROLE_USER"))

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities
    override fun getPassword(): String = user.password
    override fun getUsername(): String = user.email
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}