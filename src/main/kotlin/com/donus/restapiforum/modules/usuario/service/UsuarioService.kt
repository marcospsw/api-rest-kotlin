package com.donus.restapiforum.modules.usuario.service

import com.donus.restapiforum.modules.common.exception.NotFoundException
import com.donus.restapiforum.modules.usuario.dto.UsuarioRequestDTO
import com.donus.restapiforum.modules.usuario.dto.UsuarioResponseDTO
import com.donus.restapiforum.modules.usuario.mapper.UsuarioMapper
import com.donus.restapiforum.modules.usuario.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private var usuarios: MutableList<Usuario> = mutableListOf(),
    private val usuarioMapper: UsuarioMapper
) {
    fun list(): List<UsuarioResponseDTO> {
        return usuarios.map { usuario ->
            usuarioMapper.entityToDTO(usuario)
        }
    }

    fun findById(id: Long): UsuarioResponseDTO {
        val usuario = usuarios.find { it.id == id } ?: throw NotFoundException("Usuario não encontrado")
        return usuarioMapper.entityToDTO(usuario)
    }

    fun findEntityById(id: Long): Usuario {
        return usuarios.find { it.id == id } ?: throw NotFoundException("Usuario não encontrado")
    }

    fun create(dto: UsuarioRequestDTO): UsuarioResponseDTO {
        val usuario = usuarioMapper.dtoToEntity(dto)
        usuario.id = usuarios.size.toLong() + 1
        usuarios.add(usuario)

        return usuarioMapper.entityToDTO(usuario)
    }

    fun update(id: Long, dto: UsuarioRequestDTO): UsuarioResponseDTO {
        val usuario = usuarios.find { it.id == id }!!
        val index = usuarios.indexOf(usuario)

        usuarios[index] = usuarioMapper.dtoToEntity(dto)
        usuarios[index].id = id
        return usuarioMapper.entityToDTO(usuarios[index])
    }

    fun delete(id: Long) {
        val usuario = usuarios.find { it.id == id } ?: throw NotFoundException("Usuario não encontrado")
        usuarios.remove(usuario)
    }
}