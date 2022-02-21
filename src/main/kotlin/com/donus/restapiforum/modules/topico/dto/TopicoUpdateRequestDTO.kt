package com.donus.restapiforum.modules.topico.dto

import com.donus.restapiforum.modules.topico.model.StatusTopico
import javax.validation.constraints.NotEmpty

data class TopicoUpdateRequestDTO(
    @field:NotEmpty
    val titulo: String,

    @field:NotEmpty
    val mensagem: String,

    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
)