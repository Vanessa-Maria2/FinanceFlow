package com.ufrn.br.financeflow.mapper

import com.ufrn.br.financeflow.dtos.PersonDto
import com.ufrn.br.financeflow.dtos.PersonResponseDto
import com.ufrn.br.financeflow.models.Person
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PersonMapper {

    fun mapToDto(person: Person): PersonDto

    fun mapToEntity(personDto: PersonDto): Person

    fun mapToResponseDto(person: Person): PersonResponseDto
}