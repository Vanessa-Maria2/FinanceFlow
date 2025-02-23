package com.ufrn.br.financeflow.dtos

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

class PersonDto {

    var id: Long = 0

    @NotBlank(message = "O campo nome é obrigatório")
    var name: String? = null

    @NotBlank(message = "O campo email é obrigatório")
    @Email
    var email: String? = null

    @NotBlank(message = "O campo password é obrigatório")
    @Pattern(regexp = "^[a-zA-Z0-9_]{5,8}$", message = "A senha deve ter entre 5 e 8 caracteres e conter apenas letras, números e _")
    var password: String? = null

    var finances: MutableList<FinanceDto> = mutableListOf()
}