package com.ufrn.br.financeflow.models

import jakarta.persistence.*

@Entity
@Table(name = "type_category")
class TypeCategory {

    constructor(name: String) {
        this.name = name
    }

    constructor() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false)
    var name: String = ""
}