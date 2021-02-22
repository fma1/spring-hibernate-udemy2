package com.luv2code.springdemo.controller

import com.luv2code.springdemo.entity.Customer
import com.luv2code.springdemo.exception.CustomerNotFoundException
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

  @PostMapping(Array("/customers"))
  def addCustomer(@RequestBody customer: Customer): Customer = {
    // saveOrUpdate checks primary key
    // primary key of 0 means INSERT rather than UPDATE
    customer.setId(0)
    customerService.saveCustomer(customer)
    customer
  }

  @PutMapping(Array("/customers"))
  def updateCustomer(@RequestBody customer: Customer): Customer = {
    customerService.saveCustomer(customer)
    customer
  }

  @DeleteMapping(Array("/customers/{customerId}"))
  def deleteCustomer(@PathVariable customerId: Int): String = {
    Option(customerService.getCustomerById(customerId)) match {
      case Some(_) =>
        customerService.deleteCustomer(customerId)
        s"Deleted customer id - $customerId"
      case None =>
        throw new CustomerNotFoundException(s"Customer id not found - $customerId")
    }
  }
}
