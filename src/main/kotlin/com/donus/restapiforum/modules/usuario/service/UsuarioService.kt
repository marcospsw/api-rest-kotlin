package com.donus.restapiforum.modules.usuario.service

import com.donus.restapiforum.modules.common.exception.NotFoundException
import com.donus.restapiforum.modules.usuario.dto.UsuarioRequestDTO
import com.donus.restapiforum.modules.usuario.dto.UsuarioResponseDTO
import com.donus.restapiforum.modules.usuario.mapper.UsuarioMapper
import com.donus.restapiforum.modules.usuario.model.Usuario
import com.donus.restapiforum.modules.usuario.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val repository: UsuarioRepository,
    private val usuarioMapper: UsuarioMapper
) : UserDetailsService {
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
        repository.save(usuario.copy(password = BCryptPasswordEncoder().encode(usuario.password)))

        return usuarioMapper.entityToDTO(usuario)
    }

    fun update(id: Long, dto: UsuarioRequestDTO): UsuarioResponseDTO {
        val usuario = repository.findById(id).orElseGet { throw NotFoundException("Usuario não encontrado") }
        val novoUsuario = repository.save(usuario.copy(nome = dto.nome, email = dto.email))

        return usuarioMapper.entityToDTO(novoUsuario)
    }

    fun delete(id: Long) {
        val usuario = repository.findById(id).orElseGet { throw NotFoundException("Usuario não encontrado") }
        repository.delete(usuario)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = repository.findByEmail(email = username) ?: throw NotFoundException("Usuario não encontrado")
        return UserDetail(usuario)
    }
}
