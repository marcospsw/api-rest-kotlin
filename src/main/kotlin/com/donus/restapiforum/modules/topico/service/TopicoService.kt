package com.donus.restapiforum.modules.topico.service

import com.donus.restapiforum.modules.common.exception.NotFoundException
import com.donus.restapiforum.modules.topico.dto.TopicoByCategoriaDTO
import com.donus.restapiforum.modules.topico.dto.TopicoRequestDTO
import com.donus.restapiforum.modules.topico.dto.TopicoResponseDTO
import com.donus.restapiforum.modules.topico.dto.TopicoUpdateRequestDTO
import com.donus.restapiforum.modules.topico.mapper.TopicoMapper
import com.donus.restapiforum.modules.topico.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoMapper: TopicoMapper,
) {
    fun list(
        nomeCurso: String?,
        @PageableDefault(size = 5, sort = ["data_criacao"], direction = Sort.Direction.DESC) page: Pageable
    ): Page<TopicoResponseDTO> {
        if (nomeCurso != null) {
            return repository.getByCursoNome(nomeCurso, page).map { topico ->
                topicoMapper.entityToDTO(topico)
            }
        }
        return repository.findAll(page).map { topico ->
            topicoMapper.entityToDTO(topico)
        }
    }

    fun findById(id: Long): TopicoResponseDTO {
        val topico = repository.findById(id).orElseThrow { throw NotFoundException("Topico não encontrado") }
        return topicoMapper.entityToDTO(topico)
    }

    fun create(dto: TopicoRequestDTO): TopicoResponseDTO {
        val topico = topicoMapper.dtoToEntity(dto)
        repository.save(topico)

        return topicoMapper.entityToDTO(topico)
    }

    fun update(id: Long, dto: TopicoUpdateRequestDTO): TopicoResponseDTO {
        val topico = repository.findById(id).orElseThrow { throw NotFoundException("Topico não encontrado") }
        topico.titulo = dto.titulo
        topico.mensagem = dto.mensagem
        topico.status = dto.status

        return topicoMapper.entityToDTO(topico)
    }

    fun delete(id: Long) {
        val topico = repository.findById(id).orElseThrow { throw NotFoundException("Topico não encontrado") }
        repository.delete(topico)
    }

    fun relatorio(): List<TopicoByCategoriaDTO> {
        return repository.relatorio()
    }
}
