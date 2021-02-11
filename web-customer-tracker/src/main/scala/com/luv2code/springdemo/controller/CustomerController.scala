package com.luv2code.springdemo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(Array("/customer"))
class CustomerController {
  @RequestMapping(Array("/list"))
  def listCustomers(): String = {
    println("HELLO")
    "list-customers"
  }
}
