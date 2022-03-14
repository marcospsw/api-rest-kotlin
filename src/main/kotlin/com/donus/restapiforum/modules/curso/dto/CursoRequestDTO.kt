package com.donus.restapiforum.modules.curso.dto

import javax.validation.constraints.NotEmpty

data class CursoRequestDTO(
    val id: Long? = null,

    @field:NotEmpty
    val nome: String,

    @field:NotEmpty
    val categoria: String
)
