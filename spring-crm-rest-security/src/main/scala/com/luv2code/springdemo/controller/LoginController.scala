package com.luv2code.springdemo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
  @GetMapping(Array("/showMyLoginPage"))
  def showHome2(): String = "fancy-login"

  @GetMapping(Array("/access-denied"))
  def showAccessDenied(): String = "access-denied"
}
