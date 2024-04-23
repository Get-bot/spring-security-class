package com.ezbytes.config

import com.ezbytes.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetails(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {

        val user = userRepository.findByUsername(username).orElseThrow{
            UsernameNotFoundException("User not found")
        }

        val authorities = user.authorities.map { authority ->
            SimpleGrantedAuthority("ROLE_${authority.authority}")
        }

        return User(user.username, user.password, authorities)
    }
}