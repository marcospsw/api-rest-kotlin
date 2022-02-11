package com.donus.restapiforum.modules.curso.services

import com.donus.restapiforum.modules.curso.dto.CursoRequestDTO
import com.donus.restapiforum.modules.curso.dto.CursoResponseDTO
import com.donus.restapiforum.modules.curso.mapper.CursoMapper
import com.donus.restapiforum.modules.curso.model.Curso
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(private var cursos: MutableList<Curso> = mutableListOf(), private val cursoMapper: CursoMapper) {
    init {
        val curso1: Curso = Curso(id = 1, nome = "Kotlin", categoria = "programação")
        val curso2: Curso = Curso(id = 2, nome = "Java", categoria = "programação")
        val curso3: Curso = Curso(id = 3, nome = "Javascript", categoria = "programação")

        cursos.addAll(mutableListOf(curso1, curso2, curso3))
    }

    fun list(): List<CursoResponseDTO> {
        return cursos.map { curso ->
            cursoMapper.entityToDTO(curso)
        }
    }

    fun findById(id: Long): CursoResponseDTO {
        return cursoMapper.entityToDTO(cursos.find { it.id == id }!!)
    }

    fun findEntityById(id: Long): Curso {
        return cursos.find { it.id == id }!!
    }

    fun create(dto: CursoRequestDTO): CursoResponseDTO {
        val curso = cursoMapper.dtoToEntity(dto)
        curso.id = cursos.size.toLong() + 1
        cursos.add(curso)
        return cursoMapper.entityToDTO(curso)
    }

    fun listByCategory(category: String): List<CursoResponseDTO> {
        val cursoByCategory = cursos.filter {
            it.categoria == category.lowercase(Locale.getDefault())
        }

        val cursosMapped = mutableListOf<CursoResponseDTO>()
        cursoByCategory.map { curso ->
            cursosMapped.add(cursoMapper.entityToDTO(curso))
        }

        return cursosMapped
    }

    fun update(id: Long, dto: CursoRequestDTO): CursoResponseDTO {
        val curso = cursos.find { it.id == id }!!
        val index = cursos.indexOf(curso)

        cursos[index] = cursoMapper.dtoToEntity(dto)
        cursos[index].id = id
        return cursoMapper.entityToDTO(cursos[index])
    }

    fun delete(id: Long) {
        cursos.remove(cursos.find { it.id == id }!!)
    }
}