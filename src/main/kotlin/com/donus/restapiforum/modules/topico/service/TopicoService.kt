package com.donus.restapiforum.modules.topico.service

import com.donus.restapiforum.modules.common.exception.NotFoundException
import com.donus.restapiforum.modules.curso.services.CursoService
import com.donus.restapiforum.modules.topico.dto.TopicoRequestDTO
import com.donus.restapiforum.modules.topico.dto.TopicoResponseDTO
import com.donus.restapiforum.modules.topico.mapper.TopicoMapper
import com.donus.restapiforum.modules.topico.model.Topico
import com.donus.restapiforum.modules.usuario.service.UsuarioService
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: MutableList<Topico> = mutableListOf(),
    private val topicoMapper: TopicoMapper,
    cursoService: CursoService,
    usuarioService: UsuarioService,
) {
    fun list(): List<TopicoResponseDTO> {
        return topicos.map { topico ->
            topicoMapper.entityToDTO(topico)
        }
    }

    fun findById(id: Long): TopicoResponseDTO {
        val topico = topicos.find { it.id == id } ?: throw NotFoundException("Topico não encontrado")
        return topicoMapper.entityToDTO(topico)
    }

    fun create(dto: TopicoRequestDTO): TopicoResponseDTO {
        val topico = topicoMapper.dtoToEntity(dto)
        topico.id = topicos.size.toLong() + 1
        topicos.add(topico)

        return topicoMapper.entityToDTO(topico)
    }

    fun update(id: Long, dto: TopicoRequestDTO): TopicoResponseDTO {
        val topico = topicos.find { it.id == id } ?: throw NotFoundException("Topico não encontrado")
        val index = topicos.indexOf(topico)

        topicos[index] = topicoMapper.dtoToEntity(dto)
        topicos[index].id = id
        return topicoMapper.entityToDTO(topicos[index])
    }

    fun delete(id: Long) {
        val topico = topicos.find { it.id == id } ?: throw NotFoundException("Topico não encontrado")
        topicos.remove(topico)
    }
}