package com.luv2code.springdemo.controller

import com.luv2code.springdemo.controller.HelloWorldController.{MESSAGE, STUDENT_NAME}
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{RequestMapping, RequestParam}

import javax.servlet.http.HttpServletRequest

//noinspection SpringMVCViewInspection
@Controller
class HelloWorldController {
  @RequestMapping(Array("/showForm"))
  def showForm(): String = {
    "helloworld-form"
  }

  @RequestMapping(Array("/processForm"))
  def processForm(): String = {
    "helloworld"
  }

  @RequestMapping(Array("/processFormV2"))
  def letsShoutDude(request: HttpServletRequest, model: Model): String = {
    model.addAttribute(MESSAGE, s"Yo, ${request.getParameter(STUDENT_NAME).toUpperCase}!")
    "helloworld"
  }

  @RequestMapping(Array("/processFormV3"))
  def letsShoutDudeV2(request: HttpServletRequest,
                      @RequestParam(STUDENT_NAME) theName: String,
                      model: Model): String = {
    model.addAttribute(MESSAGE, s"Hey My Friend from v3! ${theName.toUpperCase}!")
    "helloworld"
  }
}

object HelloWorldController {
  final val MESSAGE = "message"
  final val STUDENT_NAME = "studentName"
}
