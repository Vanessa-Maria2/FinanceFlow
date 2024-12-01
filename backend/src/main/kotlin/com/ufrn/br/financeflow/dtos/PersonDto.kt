package com.ufrn.br.financeflow.dtos

import com.ufrn.br.financeflow.models.Finance
import jakarta.persistence.*

class PersonDto {

    var id: Long = 0

    lateinit var name: String

    lateinit var email: String

    lateinit var password: String

    var finances: MutableList<Finance> = mutableListOf()
}