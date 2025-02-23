package com.ufrn.br.financeflow.dtos

import com.ufrn.br.financeflow.models.Finance

class PersonResponseDto {

    var id: Long = 0

    lateinit var name: String

    lateinit var email: String

    var finances: MutableList<FinanceDto> = mutableListOf()
}