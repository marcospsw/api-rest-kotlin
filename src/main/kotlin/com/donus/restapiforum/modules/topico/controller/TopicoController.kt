package com.donus.restapiforum.modules.topico.controller

import com.donus.restapiforum.modules.topico.dto.TopicoRequestDTO
import com.donus.restapiforum.modules.topico.dto.TopicoResponseDTO
import com.donus.restapiforum.modules.topico.dto.TopicoUpdateRequestDTO
import com.donus.restapiforum.modules.topico.service.TopicoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
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
    @Transactional
    fun create(
        @RequestBody @Valid dto: TopicoRequestDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoResponseDTO> {
        val topico = topicoService.create(dto = dto)
        val uri = uriBuilder.path("/topicos/${topico.id}").build().toUri()
        return ResponseEntity.created(uri).body(topico)
    }

    @PutMapping("/{id}")
    @Transactional
    fun update(
        @PathVariable id: Long,
        @RequestBody @Valid dto: TopicoUpdateRequestDTO
    ): ResponseEntity<TopicoResponseDTO> {
        return ResponseEntity.ok(topicoService.update(id = id, dto = dto))
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        return topicoService.delete(id = id)
    }
}