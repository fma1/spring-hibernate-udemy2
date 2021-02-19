package com.luv2code.springdemo.rest.controller

import com.luv2code.springdemo.rest.entity.Student
import com.luv2code.springdemo.rest.exception.StudentNotFoundException
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}

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
}
