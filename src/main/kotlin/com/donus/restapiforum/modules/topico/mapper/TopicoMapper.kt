package com.donus.restapiforum.modules.topico.mapper

import com.donus.restapiforum.modules.common.`interface`.Mapper
import com.donus.restapiforum.modules.curso.services.CursoService
import com.donus.restapiforum.modules.topico.dto.TopicoRequestDTO
import com.donus.restapiforum.modules.topico.dto.TopicoResponseDTO
import com.donus.restapiforum.modules.topico.model.Topico
import com.donus.restapiforum.modules.usuario.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,
) : Mapper<Topico, TopicoRequestDTO, TopicoResponseDTO> {
    override fun entityToDTO(e: Topico): TopicoResponseDTO {
        return TopicoResponseDTO(
            titulo = e.titulo,
            mensagem = e.mensagem,
            status = e.status,
            dataCriacao = e.dataCriacao,
            autor = e.autor,
            curso = e.curso,
        )
    }

    override fun dtoToEntity(dto: TopicoRequestDTO): Topico {
        return Topico(
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            status = dto.status,
            dataCriacao = dto.dataCriacao,
            autor = usuarioService.findEntityById(dto.autorId),
            curso = cursoService.findEntityById(dto.cursoId),
        )
    }
}