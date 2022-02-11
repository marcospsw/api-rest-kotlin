package com.donus.restapiforum.modules.curso.controller

import com.donus.restapiforum.modules.curso.dto.CursoRequestDTO
import com.donus.restapiforum.modules.curso.dto.CursoResponseDTO
import com.donus.restapiforum.modules.curso.services.CursoService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/cursos")
class CursoController(private val cursoService: CursoService) {

    @GetMapping
    fun list(): List<CursoResponseDTO> {
        return cursoService.list()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): CursoResponseDTO {
        return cursoService.findById(id)
    }

    @PostMapping
    fun create(@RequestBody @Valid dto: CursoRequestDTO): CursoResponseDTO {
        return cursoService.create(dto)
    }

    @GetMapping("/categoria")
    fun listByCategory(@RequestParam category: String): List<CursoResponseDTO> {
        return cursoService.listByCategory(category)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid dto: CursoRequestDTO): CursoResponseDTO {
        return cursoService.update(id = id, dto = dto)
    }

    @DeleteMapping("/{id}")
    fun update(@PathVariable id: Long) {
        return cursoService.delete(id = id)
    }
}