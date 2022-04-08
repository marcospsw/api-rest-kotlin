package com.donus.restapiforum.modules.usuario.service

import com.donus.restapiforum.modules.usuario.model.Usuario
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val usuario: Usuario) : UserDetails {
    override fun getAuthorities() = usuario.role

    override fun getUsername() = usuario.email

    override fun getPassword() = usuario.password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}
