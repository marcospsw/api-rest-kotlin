package com.donus.restapiforum.modules.topico.repository

import com.donus.restapiforum.modules.topico.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository : JpaRepository<Topico, Long>
