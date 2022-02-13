package com.donus.restapiforum.modules.topico.model

import com.donus.restapiforum.modules.curso.model.Curso
import com.donus.restapiforum.modules.usuario.model.Usuario
import java.time.LocalDateTime

data class Topico(
    var id: Long,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val curso: Curso,
    val autor: Usuario,
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    val resposta: List<Resposta> = listOf()
)