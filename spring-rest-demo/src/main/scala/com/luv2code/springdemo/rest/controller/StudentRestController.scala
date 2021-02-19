package com.luv2code.springdemo.rest.controller

import com.luv2code.springdemo.rest.entity.Student
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}

import scala.collection.mutable.ArrayBuffer

@RestController
@RequestMapping(Array("/api"))
class StudentRestController {
  val aryBuf: ArrayBuffer[Student] = ArrayBuffer(Student("Poornima", "Patel"),
      Student("Mario", "Rossi"),
      Student("Mary", "Smith"))

  @GetMapping(Array("/students/{studentId}"))
  def getStudents(@PathVariable studentId: Int): Student = aryBuf(studentId)

  @GetMapping(Array("/students"))
  def getStudents: ArrayBuffer[Student] = aryBuf
}
