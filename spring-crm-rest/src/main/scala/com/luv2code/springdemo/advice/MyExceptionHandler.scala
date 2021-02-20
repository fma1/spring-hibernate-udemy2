package com.luv2code.springdemo.advice

import com.luv2code.springdemo.exception.ErrorResponse
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler}

@ControllerAdvice
class MyExceptionHandler {
  @ExceptionHandler
  def handleException(exception: Exception): ResponseEntity[ErrorResponse] =
    new ResponseEntity[ErrorResponse](
      ErrorResponse(HttpStatus.BAD_REQUEST.value(),
        exception.getMessage,
        System.currentTimeMillis()),
      HttpStatus.BAD_REQUEST)
}
