package com.ufrn.br.financeflow.service

import com.ufrn.br.financeflow.dtos.AuthDto
import com.ufrn.br.financeflow.dtos.PersonDto
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val personService: PersonService,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun findByEmail(authDto: AuthDto): PersonDto? {
        val person = authDto?.email?.let { personService.findByEmail(it) }
        if (person != null && passwordEncoder.matches(authDto.password, person.password)) {
            return person
        }
        return null
    }
}