package com.donus.restapiforum.controller

import com.donus.restapiforum.model.Topico
import com.donus.restapiforum.services.TopicoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/topicos")
class TopicoController(private val topicoService: TopicoService) {

    @GetMapping
    fun list(): List<Topico> {
        return topicoService.list()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Topico? {
        return topicoService.findById(id)
    }

    @PostMapping
    fun create(@RequestBody topico: Topico): Topico {
        return topicoService.create(topico)
    }
}