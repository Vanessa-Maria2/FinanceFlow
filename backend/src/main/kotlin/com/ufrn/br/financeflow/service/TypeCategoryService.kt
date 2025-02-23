package com.ufrn.br.financeflow.service


import com.ufrn.br.financeflow.dtos.TypeCategoryResponseDto
import com.ufrn.br.financeflow.mapper.TypeCategoryMapper
import com.ufrn.br.financeflow.repository.TypeCategoryRepository
import org.springframework.stereotype.Service

@Service
class TypeCategoryService(
    private val typeCategoryMapper: TypeCategoryMapper,
    private var typeCategoryRepository : TypeCategoryRepository
) {

    fun findAll(): List<TypeCategoryResponseDto> {
        return typeCategoryRepository.findAll().map { typeCategoryMapper.toResponseDto(it)}
    }
}