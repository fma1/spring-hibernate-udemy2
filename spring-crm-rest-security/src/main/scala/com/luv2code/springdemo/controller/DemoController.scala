package com.luv2code.springdemo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class DemoController {
  @GetMapping(Array("/"))
  def showHome(): String = "home"

  @GetMapping(Array("/leaders"))
  def showLeaders(): String = "leaders"

  @GetMapping(Array("/systems"))
  def showSystems(): String = "systems"
}
