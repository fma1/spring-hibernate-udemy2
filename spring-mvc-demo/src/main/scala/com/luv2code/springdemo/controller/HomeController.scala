package com.luv2code.springdemo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HomeController {
  @RequestMapping(Array("/"))
  def showMyPage(): Unit = "main-menu"
}
