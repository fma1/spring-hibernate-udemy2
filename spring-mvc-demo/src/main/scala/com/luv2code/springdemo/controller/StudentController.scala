package com.luv2code.springdemo.controller

import com.luv2code.springdemo.Student
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{ModelAttribute, RequestMapping, RequestParam}

//noinspection SpringMVCViewInspection
@Controller
@RequestMapping(Array("/student"))
class StudentController {
  @RequestMapping(Array("/showForm"))
  def showForm(model: Model): String = {
    model.addAttribute("student", new Student())
    "student-form"
  }

  @RequestMapping(Array("/processForm"))
  def processForm(@ModelAttribute("student") student: Student): String = {
    println(student)
    "student-confirmation"
  }
}


