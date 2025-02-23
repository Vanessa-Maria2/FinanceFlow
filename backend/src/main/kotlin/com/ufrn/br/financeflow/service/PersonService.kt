package com.ufrn.br.financeflow.service

import com.ufrn.br.financeflow.dtos.PersonDto
import com.ufrn.br.financeflow.dtos.PersonResponseDto
import com.ufrn.br.financeflow.mapper.PersonMapper
import com.ufrn.br.financeflow.models.Person
import com.ufrn.br.financeflow.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class PersonService (
    private val personMapper: PersonMapper,
    private var personRepository: PersonRepository
){



    fun findById(id: Long): Person{
        return personRepository.findById(id).get()
    }

    fun save(personDto: PersonDto): PersonResponseDto {
        val encodedPassword = BCryptPasswordEncoder(16).encode(personDto.password)

        val person = personMapper.mapToEntity(personDto).apply {
            password = encodedPassword
        }

        val savedPerson = personRepository.save(person)

        return personMapper.mapToResponseDto(savedPerson)
    }
}