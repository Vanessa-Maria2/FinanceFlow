package com.ufrn.br.financeflow.service

import com.ufrn.br.financeflow.dtos.TypeCategoryResponseDto
import com.ufrn.br.financeflow.mapper.TypeCategoryMapper
import com.ufrn.br.financeflow.repository.TypeCategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TypeCategoryService {

    @Autowired
    private lateinit var typeCategoryRepository : TypeCategoryRepository

    @Autowired
    private lateinit var typeCategoryMapper: TypeCategoryMapper

    fun findAll(): List<TypeCategoryResponseDto> {
        return typeCategoryRepository.findAll().map { typeCategoryMapper.toResponseDto(it)}
    }
}