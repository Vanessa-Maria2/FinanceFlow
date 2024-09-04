package com.ufrn.br.financeflow.models

import jakarta.persistence.*

@Entity
@Table(name = "type_category")
class TypeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false)
    var name: String = ""
}