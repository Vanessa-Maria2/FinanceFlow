package com.ufrn.br.financeflow.models

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "finance")
class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(nullable = false)
    var amount: Double = 0.0

    @Temporal(TemporalType.DATE)
    private var data: Date = Date()

    private var description: String = ""

    @Enumerated(EnumType.STRING)
    private var type: EnumTypeFinance = EnumTypeFinance.EXPENSE

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var typeCategories: MutableList<TypeCategory> = mutableListOf()
}