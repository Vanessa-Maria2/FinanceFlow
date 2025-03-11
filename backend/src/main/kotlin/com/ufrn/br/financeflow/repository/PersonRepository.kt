package com.ufrn.br.financeflow.repository

import com.ufrn.br.financeflow.models.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Long> {

    fun findByEmail(email: String): Person?

}