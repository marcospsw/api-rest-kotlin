package com.donus.restapiforum.services

import com.donus.restapiforum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(private var cursos: MutableList<Curso> = mutableListOf()) {
    init {
        val curso1: Curso = Curso(id = 1, nome = "Kotlin", categoria = "Programação")
        val curso2: Curso = Curso(id = 2, nome = "Java", categoria = "Programação")
        val curso3: Curso = Curso(id = 3, nome = "Javascript", categoria = "Programação")

        cursos.addAll(mutableListOf(curso1, curso2, curso3))
    }

    fun list(): List<Curso> {
        return cursos
    }

    fun findById(id: Long): Curso? {
        return cursos.find { it.id == id }
    }

    fun create(curso: Curso): Curso {
        cursos.add(curso)
        return curso
    }

    fun listByCategory(category: String): List<Curso> {
        return cursos.filter {
            it.categoria == category
        }
    }
}