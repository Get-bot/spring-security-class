package com.ezbytes.config

import com.ezbytes.repository.UserRepository
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomUsernamePwdAuthenticationProvider(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials.toString()

        val user = userRepository.findByUsername(username).orElseThrow {
            UsernameNotFoundException("User not found")
        }

        val authorities = user.authorities.map { authority ->
            SimpleGrantedAuthority("ROLE_${authority.authority}")
        }

        if (passwordEncoder.matches(password, user.password)) {
            return UsernamePasswordAuthenticationToken(username, password, authorities)
        } else {
            throw Exception("Invalid username or password")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }

}