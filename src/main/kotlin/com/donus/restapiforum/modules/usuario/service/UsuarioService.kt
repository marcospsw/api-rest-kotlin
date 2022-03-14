package com.donus.restapiforum.modules.usuario.service

import com.donus.restapiforum.modules.common.exception.NotFoundException
import com.donus.restapiforum.modules.usuario.dto.UsuarioRequestDTO
import com.donus.restapiforum.modules.usuario.dto.UsuarioResponseDTO
import com.donus.restapiforum.modules.usuario.mapper.UsuarioMapper
import com.donus.restapiforum.modules.usuario.model.Usuario
import com.donus.restapiforum.modules.usuario.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val repository: UsuarioRepository,
    private val usuarioMapper: UsuarioMapper
) {
    fun list(): List<UsuarioResponseDTO> {
        return repository.findAll().map { usuario ->
            usuarioMapper.entityToDTO(usuario)
        }
    }

    fun findById(id: Long): UsuarioResponseDTO {
        val usuario = repository.findById(id).orElseGet { throw NotFoundException("Usuario não encontrado") }
        return usuarioMapper.entityToDTO(usuario)
    }

    fun findEntityById(id: Long): Usuario {
        return repository.findById(id).orElseGet { throw NotFoundException("Usuario não encontrado") }
    }

    fun create(dto: UsuarioRequestDTO): UsuarioResponseDTO {
        val usuario = usuarioMapper.dtoToEntity(dto)
        repository.save(usuario)

        return usuarioMapper.entityToDTO(usuario)
    }

    fun update(id: Long, dto: UsuarioRequestDTO): UsuarioResponseDTO {
        val usuario = repository.findById(id).orElseGet { throw NotFoundException("Usuario não encontrado") }
        usuario.nome = dto.nome
        usuario.email = dto.email

        return usuarioMapper.entityToDTO(usuario)
    }

    fun delete(id: Long) {
        val usuario = repository.findById(id).orElseGet { throw NotFoundException("Usuario não encontrado") }
        repository.delete(usuario)
    }
}
