package com.luv2code.springsecurity.demo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
  @GetMapping(Array("/showMyLoginPage"))
  def showHome(): String = "fancy-login"
}
