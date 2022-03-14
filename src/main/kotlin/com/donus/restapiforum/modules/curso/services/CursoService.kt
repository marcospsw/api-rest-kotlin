package com.donus.restapiforum.modules.curso.services

import com.donus.restapiforum.modules.common.exception.NotFoundException
import com.donus.restapiforum.modules.curso.dto.CursoRequestDTO
import com.donus.restapiforum.modules.curso.dto.CursoResponseDTO
import com.donus.restapiforum.modules.curso.mapper.CursoMapper
import com.donus.restapiforum.modules.curso.model.Curso
import com.donus.restapiforum.modules.curso.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(private val repository: CursoRepository, private val cursoMapper: CursoMapper) {
    fun list(): List<CursoResponseDTO> {
        return repository.findAll().map { curso ->
            cursoMapper.entityToDTO(curso)
        }
    }

    fun findById(id: Long): CursoResponseDTO {
        val curso = repository.findById(id).orElseThrow { NotFoundException("Curso não encontrado") }
        return cursoMapper.entityToDTO(curso)
    }

    fun findEntityById(id: Long): Curso {
        return repository.findById(id).orElseThrow { NotFoundException("Curso não encontrado") }
    }

    fun create(dto: CursoRequestDTO): CursoResponseDTO {
        val curso = cursoMapper.dtoToEntity(dto)
        repository.save(curso)
        return cursoMapper.entityToDTO(curso)
    }

    fun listByCategory(category: String): List<CursoResponseDTO> {
        val cursoByCategory = repository.getAllByCategoria(category)

        cursoByCategory.isEmpty() && throw NotFoundException("Nenhum curso nesta categoria")

        val cursosMapped = mutableListOf<CursoResponseDTO>()
        cursoByCategory.map { curso ->
            cursosMapped.add(cursoMapper.entityToDTO(curso))
        }

        return cursosMapped
    }

    fun update(id: Long, dto: CursoRequestDTO): CursoResponseDTO {
        val curso = repository.findById(id).orElseThrow { NotFoundException("Curso não encontrado") }
        curso.nome = dto.nome
        curso.categoria = dto.categoria

        return cursoMapper.entityToDTO(curso)
    }

    fun delete(id: Long) {
        val curso = repository.findById(id).orElseThrow { NotFoundException("Curso não encontrado") }
        repository.delete(curso)
    }
}
