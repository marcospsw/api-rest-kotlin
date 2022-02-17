package com.donus.restapiforum.modules.curso.services

import com.donus.restapiforum.modules.common.exception.NotFoundException
import com.donus.restapiforum.modules.curso.dto.CursoRequestDTO
import com.donus.restapiforum.modules.curso.dto.CursoResponseDTO
import com.donus.restapiforum.modules.curso.mapper.CursoMapper
import com.donus.restapiforum.modules.curso.model.Curso
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(private var cursos: MutableList<Curso> = mutableListOf(), private val cursoMapper: CursoMapper) {
    fun list(): List<CursoResponseDTO> {
        return cursos.map { curso ->
            cursoMapper.entityToDTO(curso)
        }
    }

    fun findById(id: Long): CursoResponseDTO {
        val curso = cursos.find { it.id == id } ?: throw NotFoundException("Curso não encontrado")
        return cursoMapper.entityToDTO(curso)
    }

    fun findEntityById(id: Long): Curso {
        return cursos.find { it.id == id } ?: throw NotFoundException("Curso não encontrado")
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

        cursoByCategory.isEmpty() && throw NotFoundException("Nenhum curso nesta categoria")

        val cursosMapped = mutableListOf<CursoResponseDTO>()
        cursoByCategory.map { curso ->
            cursosMapped.add(cursoMapper.entityToDTO(curso))
        }

        return cursosMapped
    }

    fun update(id: Long, dto: CursoRequestDTO): CursoResponseDTO {
        val curso = cursos.find { it.id == id } ?: throw NotFoundException("Curso não encontrado")
        val index = cursos.indexOf(curso)

        cursos[index] = cursoMapper.dtoToEntity(dto)
        cursos[index].id = id
        return cursoMapper.entityToDTO(cursos[index])
    }

    fun delete(id: Long) {
        val curso = cursos.find { it.id == id } ?: throw NotFoundException("Curso não encontrado")
        cursos.remove(curso)
    }
}