package com.donus.restapiforum.modules.topico.dto

import com.donus.restapiforum.modules.curso.model.Curso
import com.donus.restapiforum.modules.topico.model.StatusTopico
import com.donus.restapiforum.modules.usuario.model.Usuario
import java.time.LocalDateTime

data class TopicoResponseDTO(
    val id: Long,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime,
    val curso: Curso,
    val autor: Usuario,
)