package com.ufrn.br.financeflow.repository

import com.ufrn.br.financeflow.models.TypeCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TypeCategoryRepository : JpaRepository<TypeCategory, Long> {

    fun findTypeCategoryByName(name: String): TypeCategory?
}