package com.ufrn.br.financeflow.mapper

import com.ufrn.br.financeflow.dtos.TypeCategoryDto
import com.ufrn.br.financeflow.dtos.TypeCategoryResponseDto
import com.ufrn.br.financeflow.models.TypeCategory
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface TypeCategoryMapper {

    fun toEntity(dto: TypeCategoryDto): TypeCategory

    fun toDto(typeCategory: TypeCategory): TypeCategoryDto

    fun toResponseDto(typeCategory: TypeCategory): TypeCategoryResponseDto
}