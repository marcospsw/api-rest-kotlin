package com.donus.restapiforum.modules.topico.model

import com.donus.restapiforum.modules.curso.model.Curso
import com.donus.restapiforum.modules.usuario.model.Usuario
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topico(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var titulo: String,
    var mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val curso: Curso,
    @ManyToOne
    val autor: Usuario,
    @Enumerated(value = EnumType.STRING)
    var status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    @OneToMany(mappedBy = "topico")
    val resposta: List<Resposta> = listOf()
)