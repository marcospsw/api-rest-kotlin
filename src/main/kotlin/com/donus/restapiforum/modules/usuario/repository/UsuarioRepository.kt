package com.donus.restapiforum.modules.usuario.repository

import com.donus.restapiforum.modules.usuario.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, Long> {
}