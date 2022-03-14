package com.donus.restapiforum.modules.curso.controller

import com.donus.restapiforum.modules.curso.dto.CursoRequestDTO
import com.donus.restapiforum.modules.curso.dto.CursoResponseDTO
import com.donus.restapiforum.modules.curso.services.CursoService
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
@RequestMapping("/cursos")
class CursoController(private val cursoService: CursoService) {

    @GetMapping
    fun list(): ResponseEntity<List<CursoResponseDTO>> {
        return ResponseEntity.ok(cursoService.list())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CursoResponseDTO> {
        return ResponseEntity.ok(cursoService.findById(id))
    }

    @PostMapping
    @Transactional
    fun create(
        @RequestBody @Valid dto: CursoRequestDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<CursoResponseDTO> {
        val curso = cursoService.create(dto)
        val uri = uriBuilder.path("/cursos/${curso.id}").build().toUri()

        return ResponseEntity.created(uri).body(curso)
    }

    @GetMapping("/categoria")
    fun listByCategory(@RequestParam category: String): ResponseEntity<List<CursoResponseDTO>> {
        return ResponseEntity.ok(cursoService.listByCategory(category))
    }

    @PutMapping("/{id}")
    @Transactional
    fun update(@PathVariable id: Long, @RequestBody @Valid dto: CursoRequestDTO): ResponseEntity<CursoResponseDTO> {
        return ResponseEntity.ok(cursoService.update(id = id, dto = dto))
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        return cursoService.delete(id = id)
    }
}
