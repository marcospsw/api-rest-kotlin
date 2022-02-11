package com.donus.restapiforum.modules.usuario.model

data class Usuario(
    var id: Long? = null,
    val nome: String,
    val email: String
)