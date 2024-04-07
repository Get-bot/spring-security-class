package com.ezbytes.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class WebSecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        /**
         * 사용자 정의 보안 구성을 위한 메서드
         */
//        return http
//            .authorizeHttpRequests { authorizeRequests ->
//                authorizeRequests
//                    .anyRequest().denyAll()
//            }
//            .formLogin { }
//            .httpBasic { }
//            .build()

//        return http
//            .authorizeHttpRequests { authorizeRequests ->
//                authorizeRequests
//                    .anyRequest().permitAll()
//            }
//            .formLogin { }
//            .httpBasic { }
//            .build()

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
