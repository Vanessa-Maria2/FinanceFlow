package com.ufrn.br.financeflow.controllers

import com.ufrn.br.financeflow.dtos.PersonDto
import com.ufrn.br.financeflow.dtos.PersonResponseDto
import com.ufrn.br.financeflow.models.Person
import com.ufrn.br.financeflow.service.PersonService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController(
    private var personService: PersonService
) {

    @GetMapping("{id}")
    fun getPerson(@PathVariable id: Long): ResponseEntity<Person> {
        return ResponseEntity.ok(personService.findById(id))
    }

    @PostMapping
    fun createPerson(@Valid @RequestBody personDto: PersonDto): ResponseEntity<PersonResponseDto> {
        return ResponseEntity.ok(personService.save(personDto))
    }
}