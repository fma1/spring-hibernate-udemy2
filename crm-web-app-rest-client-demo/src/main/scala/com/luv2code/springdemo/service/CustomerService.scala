package com.luv2code.springdemo.service

import com.luv2code.springdemo.entity.Customer

import java.util.{List => JList}

trait CustomerService {
  def getCustomerById(customerId: Int): Customer
  def getCustomers: JList[Customer]
  def saveCustomer(customer: Customer): Unit
  def deleteCustomer(customerId: Int): Unit
}
