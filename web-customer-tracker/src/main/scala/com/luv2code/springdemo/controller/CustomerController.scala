package com.luv2code.springdemo.controller

import com.luv2code.springdemo.dao.CustomerDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.ui.Model


@Controller
@RequestMapping(Array("/customer"))
class CustomerController {
  @Autowired
  private var customerDAO: CustomerDAO = _

  @RequestMapping(Array("/list"))
  def listCustomers(model: Model): String = {
    model.addAttribute("customers", customerDAO.getCustomers)
    "list-customers"
  }
}
