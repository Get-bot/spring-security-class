package com.ezbytes.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class WebSecurityConfig {

    @Bean
    fun InMemoryUserDetailsManager (): InMemoryUserDetailsManager {
        val user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("1234")
            .roles("USER")
            .build()
        val admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("1234")
            .roles("ADMIN")
            .build()
        return InMemoryUserDetailsManager(admin, user)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {

        return http
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                    .requestMatchers("/notices", "/contact").permitAll()
            }
            .formLogin { }
            .httpBasic { }
            .build()
    }
}
