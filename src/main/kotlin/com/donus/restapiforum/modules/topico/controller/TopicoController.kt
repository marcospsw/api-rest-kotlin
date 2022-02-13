package com.donus.restapiforum.modules.topico.controller

import com.donus.restapiforum.modules.topico.dto.TopicoRequestDTO
import com.donus.restapiforum.modules.topico.dto.TopicoResponseDTO
import com.donus.restapiforum.modules.topico.service.TopicoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(private val topicoService: TopicoService) {

    @GetMapping
    fun list(): ResponseEntity<List<TopicoResponseDTO>> {
        return ResponseEntity.ok(topicoService.list())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<TopicoResponseDTO> {
        return ResponseEntity.ok(topicoService.findById(id))
    }

    @PostMapping
    fun create(
        @RequestBody @Valid dto: TopicoRequestDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoResponseDTO> {
        val topico = topicoService.create(dto = dto)
        val uri = uriBuilder.path("/topicos/${topico.id}").build().toUri()
        return ResponseEntity.created(uri).body(topico)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid dto: TopicoRequestDTO): ResponseEntity<TopicoResponseDTO> {
        return ResponseEntity.ok(topicoService.update(id = id, dto = dto))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        return topicoService.delete(id = id)
    }
}