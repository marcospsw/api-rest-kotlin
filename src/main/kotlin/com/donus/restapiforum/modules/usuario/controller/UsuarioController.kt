package com.donus.restapiforum.modules.usuario.controller

import com.donus.restapiforum.modules.usuario.dto.UsuarioRequestDTO
import com.donus.restapiforum.modules.usuario.dto.UsuarioResponseDTO
import com.donus.restapiforum.modules.usuario.service.UsuarioService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/usuarios")
class UsuarioController(private val usuarioService: UsuarioService) {
    @GetMapping
    fun list(): ResponseEntity<List<UsuarioResponseDTO>> {
        return ResponseEntity.ok(usuarioService.list())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<UsuarioResponseDTO> {
        return ResponseEntity.ok(usuarioService.findById(id))
    }

    @PostMapping
    @Transactional
    fun create(
        @RequestBody @Valid dto: UsuarioRequestDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UsuarioResponseDTO> {
        val usuario = usuarioService.create(dto)
        val uri = uriBuilder.path("/usuario/${usuario.id}").build().toUri()
        return ResponseEntity.created(uri).body(usuario)
    }

    @PutMapping("/{id}")
    @Transactional
    fun update(@PathVariable id: Long, @RequestBody @Valid dto: UsuarioRequestDTO): ResponseEntity<UsuarioResponseDTO> {
        return ResponseEntity.ok(usuarioService.update(id = id, dto = dto))
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        return usuarioService.delete(id = id)
    }
}
