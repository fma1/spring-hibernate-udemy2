package com.luv2code.springdemo.dao

import com.luv2code.springdemo.entity.Customer
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

import java.util.{List => JList}

@Repository
class CustomerDAO {
  @Autowired
  private var sessionFactory: SessionFactory = _

  def getCustomerById(customerId: Int): Customer = {
    sessionFactory.getCurrentSession
      .get(classOf[Customer], customerId)
  }

  def getCustomers: JList[Customer] = {
    sessionFactory.getCurrentSession
      .createQuery("from Customer order by lastName", classOf[Customer])
      .getResultList
  }

  def saveCustomer(customer: Customer): Unit = {
    sessionFactory.getCurrentSession
      .saveOrUpdate(customer)
  }

  def deleteCustomer(customerId: Int): Unit = {
    val customer = getCustomerById(customerId)
    sessionFactory.getCurrentSession.delete(customer)
  }
}
