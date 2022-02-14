package com.donus.restapiforum.modules.common.exception

import com.donus.restapiforum.modules.common.dto.ErrorDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(exception: NotFoundException, request: HttpServletRequest): ErrorDTO {
        return ErrorDTO(
            message = exception.message,
            statusCode = HttpStatus.NOT_FOUND.value(),
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(exception: MethodArgumentNotValidException, request: HttpServletRequest): ErrorDTO {
        val messages = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach { e ->
            messages[e.field] = e.defaultMessage
        }
        
        return ErrorDTO(
            message = messages.toString(),
            statusCode = HttpStatus.BAD_REQUEST.value(),
            path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerException(exception: Exception, request: HttpServletRequest): ErrorDTO {
        return ErrorDTO(
            message = exception.message,
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            path = request.servletPath
        )
    }
}