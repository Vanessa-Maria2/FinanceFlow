package com.ufrn.br.financeflow.controllers

import com.ufrn.br.financeflow.dtos.FinanceDto
import com.ufrn.br.financeflow.models.Finance
import com.ufrn.br.financeflow.service.FinanceRecordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/finance-record")
class FinanceRecordController {

    @Autowired
    private lateinit var financeRecordService: FinanceRecordService

    @PostMapping
    fun create(@RequestBody financeDto: FinanceDto) : ResponseEntity<Finance> {
        val savedFinance = financeRecordService.createRecord(financeDto)
        return ResponseEntity.ok(savedFinance)
    }
}