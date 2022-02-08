package com.donus.restapiforum.controller

import com.donus.restapiforum.model.Curso
import com.donus.restapiforum.services.CursoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cursos")
class CursoController(private val cursoService: CursoService) {

    @GetMapping
    fun list(): List<Curso> {
        return cursoService.list()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Curso? {
        return cursoService.findById(id)
    }

    @PostMapping
    fun create(@RequestBody curso: Curso): Curso {
        return cursoService.create(curso)
    }

    @GetMapping("/teste")
    fun listByCategory(@RequestParam category: String): Unit {
        println("Teste de variavel $category")
//        return cursoService.listByCategory(category)
    }
}