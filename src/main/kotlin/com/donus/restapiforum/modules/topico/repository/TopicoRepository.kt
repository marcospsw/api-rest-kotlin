package com.donus.restapiforum.modules.topico.repository

import com.donus.restapiforum.modules.topico.dto.TopicoByCategoriaDTO
import com.donus.restapiforum.modules.topico.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository : JpaRepository<Topico, Long> {
    fun getByCursoNome(nomeCurso: String, page: Pageable): Page<Topico>

    @Query("select new com.donus.restapiforum.modules.topico.dto.TopicoByCategoriaDTO(curso.categoria, count(t)) from Topico t join t.curso curso group by curso.categoria")
    fun relatorio(): List<TopicoByCategoriaDTO>
}
