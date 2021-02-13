package com.luv2code.springdemo.service

import com.luv2code.springdemo.dao.CustomerDAO
import com.luv2code.springdemo.entity.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

import java.util.{List => JList}
import javax.transaction.Transactional

@Repository
class CustomerService {
  @Autowired
  private var customerDAO: CustomerDAO = _

  @Transactional
  def getCustomerById(customerId: Int): Customer = {
    customerDAO.getCustomerById(customerId)
  }

  @Transactional
  def getCustomers: JList[Customer] = {
    customerDAO.getCustomers
  }

  @Transactional
  def saveCustomer(customer: Customer): Unit = {
    customerDAO.saveCustomer(customer)
  }
}
