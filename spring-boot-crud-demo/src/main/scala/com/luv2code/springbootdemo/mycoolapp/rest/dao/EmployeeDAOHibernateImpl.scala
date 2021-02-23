package com.luv2code.springbootdemo.mycoolapp.rest.dao

import com.luv2code.springbootdemo.mycoolapp.rest.entity.Employee
import org.hibernate.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

import java.util.{List => JList}
import javax.persistence.EntityManager

@Repository
class EmployeeDAOHibernateImpl extends EmployeeDAO {
  @Autowired
  private var entityManager: EntityManager = _

  override def findAll(): JList[Employee] = {
    val currentSession = entityManager.unwrap(classOf[Session])
    currentSession.createQuery("from Employee", classOf[Employee])
      .getResultList
  }
}
