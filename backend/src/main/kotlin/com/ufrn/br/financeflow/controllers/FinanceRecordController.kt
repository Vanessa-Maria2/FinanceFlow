package com.ufrn.br.financeflow.controllers

import com.ufrn.br.financeflow.dtos.FinanceDto
import com.ufrn.br.financeflow.dtos.FinanceResponseDto
import com.ufrn.br.financeflow.dtos.ResponseCollectionInfo
import com.ufrn.br.financeflow.service.FinanceRecordService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/finance-record")
class FinanceRecordController(
    private var financeRecordService: FinanceRecordService
) {

    @GetMapping
    fun findAll(@RequestParam(value = "page", defaultValue = "0") page: Int,
                @RequestParam(value = "pageSize", defaultValue = "10") pageSize: Int): ResponseEntity<ResponseCollectionInfo<FinanceResponseDto>> {
        val finances = financeRecordService.findAll(page, pageSize)
        val response = ResponseCollectionInfo(
            hasNext = !finances.isLast,
            items = finances.content,
            page = finances.number,
            size = finances.size,
            totalItems = finances.totalElements,
        )
        return ResponseEntity.ok(response)
    }

    @PostMapping("/{id}")
    fun create(@RequestBody financeDto: FinanceDto, @PathVariable id: Long) : ResponseEntity<FinanceResponseDto> {
        val savedFinance = financeRecordService.createRecord(financeDto, id)
        return ResponseEntity.ok(savedFinance)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody financeDto: FinanceDto, @PathVariable id: Long) : ResponseEntity<FinanceResponseDto> {
        val updatedFinance = financeRecordService.updateRecord(financeDto, id);
        return ResponseEntity.ok(updatedFinance)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Void> {
        var isDeleted = financeRecordService.delete(id)
        return if (isDeleted)  {
            return ResponseEntity.noContent().build()
        } else {
            return ResponseEntity.notFound().build()
        }
    }
}