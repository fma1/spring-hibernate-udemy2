package com.luv2code.springdemo.controller

import com.luv2code.springdemo.entity.Customer
import com.luv2code.springdemo.service.{CustomerService, CustomerServiceImpl}
import org.springframework.beans.factory.annotation.{Autowired, Qualifier}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{GetMapping, ModelAttribute, PostMapping, RequestMapping, RequestParam}
import org.springframework.ui.Model


@Controller
@RequestMapping(Array("/customer"))
class CustomerController {
  @Autowired
  @Qualifier("customerServiceRestClientImpl")
  private var customerService: CustomerService = _

  @GetMapping(Array("/list"))
  def listCustomers(model: Model): String = {
    model.addAttribute("customers", customerService.getCustomers)
    "list-customers"
  }

  @PostMapping(Array("/saveCustomer"))
  def saveCustomer(@ModelAttribute customer: Customer): String = {
    customerService.saveCustomer(customer)
    "redirect:/customer/list"
  }

  @GetMapping(Array("/deleteCustomer"))
  def deleteCustomer(@RequestParam customerId: Int): String = {
    customerService.deleteCustomer(customerId)
    "redirect:/customer/list"
  }

  @GetMapping(Array("/showFormForAdd"))
  def showFormForAdd(model: Model): String = {
    model.addAttribute("customer", new Customer())
    "customer-form"
  }

  @GetMapping(Array("/showFormForUpdate"))
  def showFormForUpdate(@RequestParam customerId: Int, model: Model): String = {
    model.addAttribute("customer", customerService.getCustomerById(customerId))
    "customer-form"
  }
}
