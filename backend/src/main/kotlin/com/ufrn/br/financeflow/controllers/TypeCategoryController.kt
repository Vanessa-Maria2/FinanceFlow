package com.ufrn.br.financeflow.controllers

import com.ufrn.br.financeflow.dtos.TypeCategoryResponseDto
import com.ufrn.br.financeflow.service.TypeCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/type-category")
class TypeCategoryController(
    private var typeCategoryService: TypeCategoryService
) {

    @GetMapping
    fun findAll() : ResponseEntity<Collection<TypeCategoryResponseDto>> {
        var typeCategories = typeCategoryService.findAll()
        return ResponseEntity.ok(typeCategories)
    }
}