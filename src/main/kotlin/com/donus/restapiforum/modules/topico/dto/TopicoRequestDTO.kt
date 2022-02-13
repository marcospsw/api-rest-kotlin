package com.donus.restapiforum.modules.topico.dto

import com.donus.restapiforum.modules.topico.model.StatusTopico
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty

data class TopicoRequestDTO(
    val id: Long? = null,

    @field:NotEmpty
    val titulo: String,

    @field:NotEmpty
    val mensagem: String,

    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,

    @field:NotEmpty
    val cursoId: Long,

    @field:NotEmpty
    val autorId: Long,
)