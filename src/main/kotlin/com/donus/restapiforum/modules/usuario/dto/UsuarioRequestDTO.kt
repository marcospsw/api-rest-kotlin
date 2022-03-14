package com.donus.restapiforum.modules.usuario.dto

import javax.validation.constraints.NotEmpty

data class UsuarioRequestDTO(
    val id: Long? = null,

    @field:NotEmpty
    val nome: String,

    @field:NotEmpty
    val email: String
)
