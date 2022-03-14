package com.donus.restapiforum.modules.usuario.dto

data class UsuarioResponseDTO(
    val id: Long? = null,
    val nome: String,
    val email: String
)
