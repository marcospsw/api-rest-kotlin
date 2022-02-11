package com.donus.restapiforum.modules.usuario.controller

import com.donus.restapiforum.modules.usuario.dto.UsuarioRequestDTO
import com.donus.restapiforum.modules.usuario.dto.UsuarioResponseDTO
import com.donus.restapiforum.modules.usuario.service.UsuarioService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/usuarios")
class UsuarioController(private val usuarioService: UsuarioService) {
    @GetMapping
    fun list(): List<UsuarioResponseDTO> {
        return usuarioService.list()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): UsuarioResponseDTO {
        return usuarioService.findById(id)
    }

    @PostMapping
    fun create(@RequestBody @Valid dto: UsuarioRequestDTO): UsuarioResponseDTO {
        return usuarioService.create(dto)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid dto: UsuarioRequestDTO): UsuarioResponseDTO {
        return usuarioService.update(id = id, dto = dto)
    }

    @DeleteMapping("/{id}")
    fun update(@PathVariable id: Long) {
        return usuarioService.delete(id = id)
    }
}