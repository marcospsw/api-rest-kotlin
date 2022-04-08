package com.donus.restapiforum.modules.usuario.mapper

import com.donus.restapiforum.modules.common.`interface`.Mapper
import com.donus.restapiforum.modules.usuario.dto.UsuarioRequestDTO
import com.donus.restapiforum.modules.usuario.dto.UsuarioResponseDTO
import com.donus.restapiforum.modules.usuario.model.Usuario
import org.springframework.stereotype.Component

@Component
class UsuarioMapper : Mapper<Usuario, UsuarioRequestDTO, UsuarioResponseDTO> {
    override fun entityToDTO(e: Usuario): UsuarioResponseDTO {
        return UsuarioResponseDTO(
            id = e.id,
            nome = e.nome,
            email = e.email,
        )
    }

    override fun dtoToEntity(dto: UsuarioRequestDTO): Usuario {
        return Usuario(
            id = dto.id,
            nome = dto.nome,
            email = dto.email,
            password = dto.password
        )
    }
}
