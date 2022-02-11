package com.donus.restapiforum.modules.usuario.service

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
    init {
        val usuario1: Usuario = Usuario(id = 1, nome = "Marcos Wergles", email = "marcospsw96@gmail.com")
        val usuario2: Usuario = Usuario(id = 2, nome = "Camile Rodrigues", email = "camile@gmail.com")
        val usuario3: Usuario = Usuario(id = 3, nome = "Tiago Souza", email = "tiago@gmail.com")

        usuarios.addAll(mutableListOf(usuario1, usuario2, usuario3))
    }

    fun list(): List<UsuarioResponseDTO> {
        return usuarios.map { usuario ->
            usuarioMapper.entityToDTO(usuario)
        }
    }

    fun findById(id: Long): UsuarioResponseDTO {
        return usuarioMapper.entityToDTO(usuarios.find { it.id == id }!!)
    }

    fun findEntityById(id: Long): Usuario {
        return usuarios.find { it.id == id }!!
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
        usuarios.remove(usuarios.find { it.id == id }!!)
    }
}