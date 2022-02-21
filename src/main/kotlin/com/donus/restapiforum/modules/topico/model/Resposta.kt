package com.donus.restapiforum.modules.topico.model

import com.donus.restapiforum.modules.usuario.model.Usuario
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Resposta(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val autor: Usuario,
    @ManyToOne
    val topico: Topico,
    val solucao: Boolean
)