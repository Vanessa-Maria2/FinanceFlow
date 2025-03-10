package com.ufrn.br.financeflow.models

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "finance")
class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false)
    var amount: Double = 0.0

    @Temporal(TemporalType.DATE)
    var data: Date = Date()

    var description: String = ""

    @Enumerated(EnumType.STRING)
    lateinit var type: EnumTypeFinance

    @ManyToMany(fetch = FetchType.LAZY)
    var typeCategories: MutableList<TypeCategory> = mutableListOf()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    lateinit var person: Person
}