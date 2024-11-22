package com.ufrn.br.financeflow.dtos

import com.ufrn.br.financeflow.models.EnumTypeFinance
import java.util.*

class FinanceResponseDto {

    var id: Long = 0

    var amount: Double = 0.0

    var data: Date = Date()

    var description: String = ""

    lateinit var type: EnumTypeFinance

    var typeCategories: MutableList<TypeCategoryDto> = mutableListOf()
}