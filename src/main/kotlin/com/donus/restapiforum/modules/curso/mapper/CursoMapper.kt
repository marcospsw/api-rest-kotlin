package com.donus.restapiforum.modules.curso.mapper

import com.donus.restapiforum.modules.common.`interface`.Mapper
import com.donus.restapiforum.modules.curso.dto.CursoRequestDTO
import com.donus.restapiforum.modules.curso.dto.CursoResponseDTO
import com.donus.restapiforum.modules.curso.model.Curso
import org.springframework.stereotype.Component

@Component
class CursoMapper : Mapper<Curso, CursoRequestDTO, CursoResponseDTO> {
    override fun entityToDTO(e: Curso): CursoResponseDTO {
        return CursoResponseDTO(
            id = e.id,
            nome = e.nome,
            categoria = e.categoria,
        )
    }

    override fun dtoToEntity(dto: CursoRequestDTO): Curso {
        return Curso(
            id = dto.id,
            nome = dto.nome,
            categoria = dto.categoria,
        )
    }
}