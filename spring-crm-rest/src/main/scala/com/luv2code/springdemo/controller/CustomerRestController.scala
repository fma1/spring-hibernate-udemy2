package com.luv2code.springdemo.controller

import com.luv2code.springdemo.entity.Customer
import com.luv2code.springdemo.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

import java.util.{List => JList}

@RestController
@RequestMapping(Array("/api"))
class CustomerRestController {
  @Autowired
  private var customerService: CustomerService = _

  @GetMapping(Array("/customers"))
  def getCustomers: JList[Customer] = customerService.getCustomers

  @GetMapping(Array("/customers/{customerId}"))
  def getCustomer(@PathVariable customerId: Int): Customer =
    customerService.getCustomerById(customerId)
}
