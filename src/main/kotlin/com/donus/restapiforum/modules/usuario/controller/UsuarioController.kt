package com.donus.restapiforum.modules.usuario.controller

import com.donus.restapiforum.modules.usuario.dto.UsuarioRequestDTO
import com.donus.restapiforum.modules.usuario.dto.UsuarioResponseDTO
import com.donus.restapiforum.modules.usuario.service.UsuarioService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
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
    fun create(
        @RequestBody @Valid dto: UsuarioRequestDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UsuarioResponseDTO> {
        val usuario = usuarioService.create(dto)
        val uri = uriBuilder.path("/usuario/${usuario.id}").build().toUri()
        return ResponseEntity.created(uri).body(usuario)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid dto: UsuarioRequestDTO): ResponseEntity<UsuarioResponseDTO> {
        return ResponseEntity.ok(usuarioService.update(id = id, dto = dto))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        return usuarioService.delete(id = id)
    }
}