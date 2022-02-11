package com.donus.restapiforum.modules.topico.controller

import com.donus.restapiforum.modules.topico.dto.TopicoRequestDTO
import com.donus.restapiforum.modules.topico.dto.TopicoResponseDTO
import com.donus.restapiforum.modules.topico.service.TopicoService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(private val topicoService: TopicoService) {

    @GetMapping
    fun list(): List<TopicoResponseDTO> {
        return topicoService.list()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TopicoResponseDTO {
        return topicoService.findById(id)
    }

    @PostMapping
    fun create(@RequestBody @Valid dto: TopicoRequestDTO): TopicoResponseDTO {
        return topicoService.create(dto = dto)
    }

    @PutMapping("/{id}")
    fun create(@PathVariable id: Long, @RequestBody @Valid dto: TopicoRequestDTO): TopicoResponseDTO {
        return topicoService.update(id = id, dto = dto)
    }

    @DeleteMapping("/{id}")
    fun create(@PathVariable id: Long) {
        return topicoService.delete(id = id)
    }
}