package com.ufrn.br.financeflow.controllers

import com.ufrn.br.financeflow.dtos.AuthDto
import com.ufrn.br.financeflow.dtos.PersonDto
import com.ufrn.br.financeflow.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/")
    fun login(@RequestBody authDto: AuthDto): ResponseEntity<PersonDto> {
        val person = authService.findByEmail(authDto)
        return if (person != null) {
            ResponseEntity.ok(person)
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }
}