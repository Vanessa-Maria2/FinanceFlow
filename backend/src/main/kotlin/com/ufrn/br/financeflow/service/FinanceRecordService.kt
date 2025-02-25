package com.ufrn.br.financeflow.service

import com.ufrn.br.financeflow.dtos.FinanceDto
import com.ufrn.br.financeflow.dtos.FinanceResponseDto
import com.ufrn.br.financeflow.mapper.FinanceMapper
import com.ufrn.br.financeflow.mapper.PersonMapper
import com.ufrn.br.financeflow.mapper.TypeCategoryMapper
import com.ufrn.br.financeflow.models.Finance
import com.ufrn.br.financeflow.models.TypeCategory
import com.ufrn.br.financeflow.repository.FinanceRecordRepository
import com.ufrn.br.financeflow.repository.PersonRepository
import com.ufrn.br.financeflow.repository.TypeCategoryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class FinanceRecordService(
    private var financeRecordRepository: FinanceRecordRepository,
    private var typeCategoryRepository: TypeCategoryRepository,
    private var personRepository: PersonRepository,
    private val financeMapper: FinanceMapper,
    private val personMapper: PersonMapper,
    private val typeCategoryMapper: TypeCategoryMapper,
) {


    fun findAll(page: Int, pageSize: Int): Page<FinanceResponseDto> {
        val pageable = PageRequest.of(page, pageSize)
        val financePage = financeRecordRepository.findAll(pageable)
        return financePage.map { financeMapper.toResponseDto(it) }
    }

    fun createRecord(finance: FinanceDto, id: Long) : FinanceResponseDto {
        val person = personRepository.findById(id).orElseThrow {
            Exception("Person with ID $id not found")
        }

        finance.person = personMapper.mapToDto(person)

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

        return financeMapper.toResponseDto(financeRecordRepository.save(financeEntity))
    }

    fun updateRecord(financeDto: FinanceDto, id: Long): FinanceResponseDto {
        val existingFinance = financeRecordRepository.findById(id).orElseThrow {
            Exception("Finance record with ID $id not found")
        }

        existingFinance.apply {
            amount = financeDto.amount
            description = financeDto.description
            type = financeDto.type
        }

        val updatedCategories = financeDto.typeCategories.map { categoryDto ->
            typeCategoryRepository.findTypeCategoryByName(categoryDto.name)
                ?: typeCategoryRepository.save(TypeCategory(categoryDto.name))
        }

        existingFinance.typeCategories.apply {
            clear()
            addAll(updatedCategories)
        }

        return financeMapper.toResponseDto(financeRecordRepository.save(existingFinance))
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