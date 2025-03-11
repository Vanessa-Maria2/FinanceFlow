package com.ufrn.br.financeflow.service

import com.ufrn.br.financeflow.dtos.PersonDto
import com.ufrn.br.financeflow.dtos.PersonResponseDto
import com.ufrn.br.financeflow.mapper.PersonMapper
import com.ufrn.br.financeflow.models.Person
import com.ufrn.br.financeflow.repository.PersonRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class PersonService (
    private val personMapper: PersonMapper,
    private var personRepository: PersonRepository,
    private val passwordEncoder: BCryptPasswordEncoder
){

    fun findById(id: Long): Person{
        return personRepository.findById(id).get()
    }

    fun save(personDto: PersonDto): PersonResponseDto {
        val encodedPassword = passwordEncoder.encode(personDto.password)

        val person = personMapper.mapToEntity(personDto).apply {
            password = encodedPassword
        }

        val savedPerson = personRepository.save(person)

        return personMapper.mapToResponseDto(savedPerson)
    }

    fun findByEmail(email: String): PersonDto? {
        var person = personRepository.findByEmail(email)
        return person?.let { personMapper.mapToDto(it) }
    }
}