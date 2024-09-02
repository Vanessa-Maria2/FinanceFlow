package com.ufrn.br.financeflow.models

import jakarta.persistence.*

@Entity
@Table(name = "person")
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0

    @Column(nullable = false)
    private lateinit var name: String

    @Column(nullable = false)
    private lateinit var email: String

    @Column(nullable = false)
    private lateinit var password: String

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    private var finances: MutableList<Finance> = mutableListOf()
}