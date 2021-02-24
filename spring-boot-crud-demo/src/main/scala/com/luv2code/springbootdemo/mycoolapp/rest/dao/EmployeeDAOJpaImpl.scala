package com.luv2code.springbootdemo.mycoolapp.rest.dao

import com.luv2code.springbootdemo.mycoolapp.rest.entity.Employee
import org.hibernate.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

import java.util.{List => JList}
import javax.persistence.EntityManager

@Repository
class EmployeeDAOJpaImpl extends EmployeeDAO {
  @Autowired
  private var entityManager: EntityManager = _

  override def findAll(): JList[Employee] = {
    entityManager.createQuery("from Employee", classOf[Employee])
      .getResultList
  }

  override def findById(id: Int): Employee = {
    entityManager.find(classOf[Employee], id)
  }

  override def save(employee: Employee): Unit = {
    entityManager.persist(employee)
  }

  override def deleteById(id: Int): Unit = {
    // entityManager.remove()
    entityManager.createQuery("delete from Employee where id=:employeeId")
      .setParameter("employeeId", id)
      .executeUpdate()
  }
}
