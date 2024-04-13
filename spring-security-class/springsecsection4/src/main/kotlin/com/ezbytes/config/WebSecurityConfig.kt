package com.ezbytes.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class WebSecurityConfig {
//    인메모리 사용자 세부 정보 관리자를 사용하여 사용자를 정의하고 사용자를 인증하는 방법
//    @Bean
//    fun InMemoryUserDetailsManager (): InMemoryUserDetailsManager {
//        val user = User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("1234")
//            .roles("USER")
//            .build()
//        val admin = User.withDefaultPasswordEncoder()
//            .username("admin")
//            .password("1234")
//            .roles("ADMIN")
//            .build()
//        return InMemoryUserDetailsManager(admin, user)
//    }

//    @Bean
//    fun userDetailsService(
//        dataSource: DataSource
//    ): UserDetailsManager{
//        val userDetailsService = JdbcUserDetailsManager()
//        userDetailsService.setDataSource(dataSource)
//        return userDetailsService
//    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }


    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {

        return http
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                    .requestMatchers("/notices", "/contact", "/register").permitAll()
            }
            .csrf{ csrf -> csrf.disable()}
            .formLogin { }
            .httpBasic { }
            .build()
    }
}
