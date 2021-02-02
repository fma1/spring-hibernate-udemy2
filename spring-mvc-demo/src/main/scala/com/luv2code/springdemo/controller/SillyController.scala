package com.luv2code.springdemo.controller

import com.luv2code.springdemo.controller.HelloWorldController.{MESSAGE, STUDENT_NAME}
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{RequestMapping, RequestParam}

import javax.servlet.http.HttpServletRequest

//noinspection SpringMVCViewInspection
@Controller
@RequestMapping(Array("/silly"))
class SillyController {
  @RequestMapping(Array("/showForm"))
  def showForm(): String = {
    "helloworld-form"
  }
}
