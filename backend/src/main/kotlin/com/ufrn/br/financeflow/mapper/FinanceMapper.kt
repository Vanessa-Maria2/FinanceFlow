package com.ufrn.br.financeflow.mapper

import com.ufrn.br.financeflow.dtos.FinanceDto
import com.ufrn.br.financeflow.dtos.FinanceResponseDto
import com.ufrn.br.financeflow.models.Finance
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", uses = [TypeCategoryMapper::class])
// @Mapper(componentModel = "spring")
interface FinanceMapper {

    fun toEntity(dto: FinanceDto): Finance

    fun toDto(finance: Finance): FinanceDto

    fun toResponseDto(finance: Finance): FinanceResponseDto
}
