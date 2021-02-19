package com.luv2code.springdemo.rest.controller

import com.luv2code.springdemo.rest.entity.Student
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/api"))
class StudentRestController {
  @GetMapping(Array("/students"))
  def getStudents: List[Student] = {
    List(Student("Poornima", "Patel"),
      Student("Mario", "Rossi"),
      Student("Mary", "Smith"))
  }
}
