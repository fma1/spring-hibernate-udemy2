package com.luv2code.springdemo.rest.controller

import com.luv2code.springdemo.rest.entity.Student
import com.luv2code.springdemo.rest.exception.{StudentErrorResponse, StudentNotFoundException}
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{ExceptionHandler, GetMapping, PathVariable, RequestMapping, RestController}

import scala.collection.mutable.ArrayBuffer
import scala.util.{Failure, Success, Try}

@RestController
@RequestMapping(Array("/api"))
class StudentRestController {
  val aryBuf: ArrayBuffer[Student] = ArrayBuffer(Student("Poornima", "Patel"),
      Student("Mario", "Rossi"),
      Student("Mary", "Smith"))

  @GetMapping(Array("/students/{studentId}"))
  def getStudents(@PathVariable studentId: Int): Student = {
    Try(aryBuf(studentId)) match {
      case Success(value) =>
        value
      case Failure(_) =>
        throw new StudentNotFoundException(s"Student id not found - $studentId")
    }
  }

  @GetMapping(Array("/students"))
  def getStudents: ArrayBuffer[Student] = aryBuf

  @ExceptionHandler
  def handleException(exception: StudentNotFoundException): ResponseEntity[StudentErrorResponse] = {
    val studentErrorResponse = StudentErrorResponse(HttpStatus.NOT_FOUND.value(),
      exception.getMessage, System.currentTimeMillis())
    new ResponseEntity[StudentErrorResponse](studentErrorResponse, HttpStatus.NOT_FOUND)
  }
}
