package com.ufrn.br.financeflow.dtos

data class ResponseCollectionInfo<T>(
    val hasNext: Boolean,
    val items: List<T>,
    val page: Int,
    val size: Int,
    val totalItems: Long,
)
