package com.donus.restapiforum.modules.topico.repository

import com.donus.restapiforum.modules.topico.model.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository : JpaRepository<Resposta, Long> {
}