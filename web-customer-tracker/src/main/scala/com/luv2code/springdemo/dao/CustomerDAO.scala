package com.luv2code.springdemo.dao

import com.luv2code.springdemo.entity.Customer
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

import java.util.{List => JList}
import javax.transaction.Transactional

@Repository
class CustomerDAO {
  @Autowired
  private var sessionFactory: SessionFactory = _

  @Transactional
  def getCustomers: JList[Customer] = {
    sessionFactory.getCurrentSession
      .createQuery("from Customer", classOf[Customer])
      .getResultList
  }
}
