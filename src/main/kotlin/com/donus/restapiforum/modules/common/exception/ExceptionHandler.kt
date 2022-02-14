package com.donus.restapiforum.modules.common.exception

import com.donus.restapiforum.modules.common.dto.ErrorDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(exception: NotFoundException): ErrorDTO {
        return ErrorDTO(message = exception.message, errorCode = HttpStatus.NOT_FOUND.value())
    }
}