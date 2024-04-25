package io.bvalentino.forum.exception.handler

import io.bvalentino.forum.dto.APIErrorResponse
import io.bvalentino.forum.exception.NotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class APIExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun notFoundExceptionHandler(
        exception: NotFoundException,
        request: HttpServletRequest
    ): APIErrorResponse {
        return APIErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): APIErrorResponse {
        val errorMessage = HashMap<String, String?>()

        exception.bindingResult.fieldErrors.forEach { fieldError ->
            errorMessage[fieldError.field] = fieldError.defaultMessage
        }

        return APIErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = errorMessage.toString(),
            path = request.servletPath
        )
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun exceptionHandler(
        exception: Exception,
        request: HttpServletRequest
    ): APIErrorResponse {
        return APIErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message,
            path = request.servletPath
        )
    }

}