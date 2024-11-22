package com.ufrn.br.financeflow.mapper

import com.ufrn.br.financeflow.dtos.FinanceDto
import com.ufrn.br.financeflow.dtos.FinanceResponseDto
import com.ufrn.br.financeflow.models.Finance
import org.springframework.stereotype.Component

@Component
class FinanceMapper {

    val typeCategoryMapper  = TypeCategoryMapper()

    fun toEntity (dto: FinanceDto): Finance {
        val finance = Finance()
        finance.id = dto.id
        finance.amount = dto.amount
        finance.type = dto.type
        finance.description = dto.description

        for (t in dto.typeCategories) {
            finance.typeCategories.add(typeCategoryMapper.toEntity(t))
        }

        return finance
    }

    fun toDto (finance: Finance): FinanceDto {
        val dto = FinanceDto()
        dto.id = finance.id
        dto.amount = finance.amount
        dto.type = finance.type
        dto.description = finance.description

        for (t in finance.typeCategories) {
            dto.typeCategories.add(typeCategoryMapper.toDto(t))
        }

        return dto
    }

    fun toResponseDto (finance: Finance): FinanceResponseDto {
        val dto = FinanceResponseDto()
        dto.id = finance.id
        dto.amount = finance.amount
        dto.type = finance.type
        dto.description = finance.description

        for (t in finance.typeCategories) {
            dto.typeCategories.add(typeCategoryMapper.toDto(t))
        }
        return dto
    }
}