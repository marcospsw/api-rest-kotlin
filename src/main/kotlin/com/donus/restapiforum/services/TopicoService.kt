package com.donus.restapiforum.services

import com.donus.restapiforum.model.Curso
import com.donus.restapiforum.model.Topico
import com.donus.restapiforum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService(private var topicos: MutableList<Topico> = mutableListOf()) {
    init {
        val topico1: Topico = Topico(
            id = 1,
            titulo = "Gradle não funciona",
            mensagem = "Meu gradle não está funcionando adequadamente",
            curso = Curso(id = 1, nome = "Kotlin", categoria = "Programação"),
            autor = Usuario(id = 1, nome = "Marcos Wergles", email = "marcospsw96@gmail.com")
        )

        val topico2: Topico = Topico(
            id = 2,
            titulo = "Gradle funciona",
            mensagem = "Meu gradle está funcionando adequadamente",
            curso = Curso(id = 1, nome = "Kotlin", categoria = "Programação"),
            autor = Usuario(id = 1, nome = "Marcos Wergles", email = "marcospsw96@gmail.com")
        )

        val topico3: Topico = Topico(
            id = 3,
            titulo = "Maven não funciona",
            mensagem = "Meu maven não está funcionando adequadamente",
            curso = Curso(id = 1, nome = "Kotlin", categoria = "Programação"),
            autor = Usuario(id = 1, nome = "Marcos Wergles", email = "marcospsw96@gmail.com")
        )

        topicos.addAll(mutableListOf(topico1, topico2, topico3))
    }

    fun list(): List<Topico> {
        return topicos
    }

    fun findById(id: Long): Topico? {
        return topicos.find { it.id == id }
    }

    fun create(topico: Topico): Topico {
        topicos.add(topico)
        return topico
    }
}