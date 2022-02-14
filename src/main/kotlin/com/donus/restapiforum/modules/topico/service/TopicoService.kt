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
    init {
        val topico1 = Topico(
            id = 1,
            titulo = "Gradle não funciona",
            mensagem = "Meu gradle não está funcionando adequadamente",
            curso = cursoService.findEntityById(1),
            autor = usuarioService.findEntityById(1)
        )

        val topico2 = Topico(
            id = 2,
            titulo = "Gradle funciona",
            mensagem = "Meu gradle está funcionando adequadamente",
            curso = cursoService.findEntityById(2),
            autor = usuarioService.findEntityById(2)
        )

        val topico3 = Topico(
            id = 3,
            titulo = "Maven não funciona",
            mensagem = "Meu maven não está funcionando adequadamente",
            curso = cursoService.findEntityById(3),
            autor = usuarioService.findEntityById(3)
        )

        topicos.addAll(mutableListOf(topico1, topico2, topico3))
    }

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