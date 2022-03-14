package com.donus.restapiforum.modules.curso.repository

import com.donus.restapiforum.modules.curso.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository : JpaRepository<Curso, Long> {
    fun getAllByCategoria(category: String): MutableList<Curso>
}
