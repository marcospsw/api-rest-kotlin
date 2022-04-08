package com.donus.restapiforum.modules.common.config

import com.donus.restapiforum.modules.usuario.model.Role
import com.donus.restapiforum.modules.usuario.service.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JWTUtils(private val usuarioService: UsuarioService) {
    private val expiration: Long = 60000

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(username: String, authorities: List<Role>): String? {
        return Jwts.builder()
            .setSubject(username)
            .claim("role", authorities)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt)
            true
        } catch (e: java.lang.IllegalArgumentException) {
            false
        }
    }

    fun getAuthenticated(jwt: String?): Authentication {
        val username = Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt).body.subject
        val user = usuarioService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }
}
