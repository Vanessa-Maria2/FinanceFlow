package com.ufrn.br.financeflow.service

import com.ufrn.br.financeflow.dtos.FinanceDto
import com.ufrn.br.financeflow.mapper.FinanceMapper
import com.ufrn.br.financeflow.models.Finance
import com.ufrn.br.financeflow.repository.FinanceRecordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FinanceRecordService {

    @Autowired
    private lateinit var financeRecordRepository: FinanceRecordRepository

    @Autowired
    private lateinit var financeMapper: FinanceMapper

    fun createRecord(finance: FinanceDto) : Finance {
        validateRecord(finance)
        return financeRecordRepository.save(financeMapper.toEntity(finance))
    }

    fun validateRecord(finance: FinanceDto) {
        if(finance.amount == null || finance.amount <= 0) {
            throw Exception("O campo amount é obrigatório")
        }

        if(finance.typeCategories.isEmpty() || finance.typeCategories.size == 0) {
            throw Exception("O categoria é obrigatório")
        }
    }
}