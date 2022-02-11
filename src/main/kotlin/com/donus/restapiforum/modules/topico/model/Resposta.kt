package com.donus.restapiforum.modules.topico.model

import com.donus.restapiforum.modules.topico.model.Topico
import java.time.LocalDateTime

data class Resposta(
    val id: Long? = null,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val autor: String,
    val topico: Topico,
    val solucao: Boolean
)