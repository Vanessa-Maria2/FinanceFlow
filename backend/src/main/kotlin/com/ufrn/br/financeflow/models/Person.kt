package com.ufrn.br.financeflow.models

import jakarta.persistence.*

@Entity
@Table(name = "person")
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(nullable = false)
    lateinit var name: String

    @Column(nullable = false)
    lateinit var email: String

    @Column(nullable = false)
    lateinit var password: String

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var finances: MutableList<Finance> = mutableListOf()
}