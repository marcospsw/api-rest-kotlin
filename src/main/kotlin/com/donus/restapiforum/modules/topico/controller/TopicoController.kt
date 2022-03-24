package com.donus.restapiforum.modules.topico.controller

import com.donus.restapiforum.modules.topico.dto.TopicoByCategoriaDTO
import com.donus.restapiforum.modules.topico.dto.TopicoRequestDTO
import com.donus.restapiforum.modules.topico.dto.TopicoResponseDTO
import com.donus.restapiforum.modules.topico.dto.TopicoUpdateRequestDTO
import com.donus.restapiforum.modules.topico.service.TopicoService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(private val topicoService: TopicoService) {

    @GetMapping
    @Cacheable("listTopicos")
    fun list(
        @RequestParam(required = false) nomeCurso: String?,
        page: Pageable
    ): ResponseEntity<Page<TopicoResponseDTO>> {
        return ResponseEntity.ok(topicoService.list(nomeCurso, page))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<TopicoResponseDTO> {
        return ResponseEntity.ok(topicoService.findById(id))
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["listTopicos"], allEntries = true)
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
    @CacheEvict(value = ["listTopicos"], allEntries = true)
    fun update(
        @PathVariable id: Long,
        @RequestBody @Valid dto: TopicoUpdateRequestDTO
    ): ResponseEntity<TopicoResponseDTO> {
        return ResponseEntity.ok(topicoService.update(id = id, dto = dto))
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = ["listTopicos"], allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        return topicoService.delete(id = id)
    }

    @GetMapping("/relatorio")
    fun relatorio(): ResponseEntity<List<TopicoByCategoriaDTO>> {
        return ResponseEntity.ok(topicoService.relatorio())
    }
}
