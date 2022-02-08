package com.donus.restapiforum.model

import java.time.LocalDateTime

data class Resposta(
    val id: Long? = null,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val autor: String,
    val topico: Topico,
    val solucao: Boolean
)