package com.ufrn.br.financeflow.mapper

import com.ufrn.br.financeflow.dtos.TypeCategoryDto
import com.ufrn.br.financeflow.models.TypeCategory
import org.springframework.stereotype.Component

@Component
class TypeCategoryMapper {

    fun toEntity (dto: TypeCategoryDto): TypeCategory {
        val typeCategory = TypeCategory()
        typeCategory.id = dto.id
        typeCategory.name = dto.name
        return typeCategory
    }

    fun toDto (typeCategory: TypeCategory): TypeCategoryDto {
        val dto = TypeCategoryDto()
        dto.id = typeCategory.id
        dto.name = typeCategory.name
        return dto
    }
}