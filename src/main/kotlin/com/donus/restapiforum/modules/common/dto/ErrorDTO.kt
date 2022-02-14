package com.donus.restapiforum.modules.common.dto

import java.time.LocalDateTime

data class ErrorDTO(
    val message: String?,
    val errorCode: Int,
    val data: LocalDateTime = LocalDateTime.now(),
)