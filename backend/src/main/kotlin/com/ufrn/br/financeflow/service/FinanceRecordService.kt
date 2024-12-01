package com.ufrn.br.financeflow.service

import com.ufrn.br.financeflow.dtos.FinanceDto
import com.ufrn.br.financeflow.dtos.FinanceResponseDto
import com.ufrn.br.financeflow.mapper.FinanceMapper
import com.ufrn.br.financeflow.mapper.PersonMapper
import com.ufrn.br.financeflow.models.Finance
import com.ufrn.br.financeflow.models.TypeCategory
import com.ufrn.br.financeflow.repository.FinanceRecordRepository
import com.ufrn.br.financeflow.repository.PersonRepository
import com.ufrn.br.financeflow.repository.TypeCategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class FinanceRecordService {

    @Autowired
    private lateinit var financeRecordRepository: FinanceRecordRepository

    @Autowired
    private lateinit var financeMapper: FinanceMapper

    @Autowired
    private lateinit var typeCategoryRepository: TypeCategoryRepository

    @Autowired
    private lateinit var personMapper: PersonMapper

    @Autowired
    private lateinit var personRepository: PersonRepository

    fun findAll(page: Int, pageSize: Int): Page<FinanceResponseDto> {
        val pageable = PageRequest.of(page, pageSize)
        val financePage = financeRecordRepository.findAll(pageable)
        return financePage.map { financeMapper.toResponseDto(it) }
    }

    fun createRecord(finance: FinanceDto, id: Long) : Finance {
        val person = personRepository.findById(id).orElseThrow {
            Exception("Person with ID $id not found")
        }

        finance.person = personMapper.toPersonDto(person)

        validateRecord(finance)

        val financeEntity = financeMapper.toEntity(finance)

        val categoriesToAssociate = finance.typeCategories.map { categoryDto ->
            typeCategoryRepository.findTypeCategoryByName(categoryDto.name)
                ?: typeCategoryRepository.save(TypeCategory(categoryDto.name))
        }

        financeEntity.typeCategories.apply {
            clear()
            addAll(categoriesToAssociate)
        }

        return financeRecordRepository.save(financeEntity)
    }

    fun validateRecord(finance: FinanceDto) {
        if (finance.amount == null || finance.amount <= 0) {
            throw Exception("O campo amount é obrigatório")
        }

        if (finance.typeCategories.isEmpty() || finance.typeCategories.size == 0) {
            throw Exception("O categoria é obrigatório")
        }
    }

    fun delete(id: Long) : Boolean {
        if (financeRecordRepository.existsById(id)) {
            financeRecordRepository.deleteById(id)
            return true
        } else {
            return false
        }
    }
}