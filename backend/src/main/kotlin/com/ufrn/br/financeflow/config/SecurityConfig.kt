package com.ufrn.br.financeflow.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder(10)
    }

    @Bean
    fun filterChain(http: HttpSecurity): DefaultSecurityFilterChain? {
        http
            .csrf{ csrf -> csrf.disable() }
            .cors{}
            .authorizeHttpRequests{
            authz ->
            authz
                .requestMatchers("/finance-record/**").permitAll()
                .requestMatchers("/type-category/**").permitAll()
                .requestMatchers("/person/**").permitAll()
                .anyRequest().authenticated()
        }

        return http.build()
    }
}