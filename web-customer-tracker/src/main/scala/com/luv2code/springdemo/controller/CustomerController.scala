package com.luv2code.springdemo.controller

import com.luv2code.springdemo.dao.CustomerDAO
import com.luv2code.springdemo.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping}
import org.springframework.ui.Model


@Controller
@RequestMapping(Array("/customer"))
class CustomerController {
  @Autowired
  private var customerService: CustomerService = _

  @GetMapping(Array("/list"))
  def listCustomers(model: Model): String = {
    model.addAttribute("customers", customerService.getCustomers)
    "list-customers"
  }
}
