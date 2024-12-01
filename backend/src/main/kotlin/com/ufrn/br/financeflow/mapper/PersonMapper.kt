package com.ufrn.br.financeflow.mapper

import com.ufrn.br.financeflow.dtos.PersonDto
import com.ufrn.br.financeflow.models.Person
import org.springframework.stereotype.Component

@Component
class PersonMapper {

    fun toPersonDto(person: Person): PersonDto {
        val personDto = PersonDto()
        personDto.id = person.id
        personDto.name = person.name
        personDto.email = person.email
        personDto.password = person.password
        return personDto
    }

    fun toPerson(personDto: PersonDto): Person {
        val person = Person()
        person.id = personDto.id
        person.name = personDto.name
        person.email = personDto.email
        person.password = personDto.password
        return person
    }
}