package com.ufrn.br.financeflow.repository

import com.ufrn.br.financeflow.models.Finance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FinanceRecordRepository : JpaRepository<Finance, Long> {

}