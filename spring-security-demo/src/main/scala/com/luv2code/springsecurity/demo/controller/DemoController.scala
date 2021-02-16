package com.luv2code.springsecurity.demo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class DemoController {
  @GetMapping(Array("/"))
  def showHome(): String = "home"

  @GetMapping(Array("/leaders"))
  def showLeaderHome(): String = "leaders"
}
