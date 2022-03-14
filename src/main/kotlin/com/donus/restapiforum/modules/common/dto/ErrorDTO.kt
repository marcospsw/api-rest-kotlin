package com.donus.restapiforum.modules.common.dto

import java.time.LocalDateTime

data class ErrorDTO(
    val message: String?,
    val statusCode: Int,
    val data: LocalDateTime = LocalDateTime.now(),
    val path: String
)
