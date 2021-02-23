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
    entityManager.unwrap(classOf[Session])
      .createQuery("from Employee", classOf[Employee])
      .getResultList
  }

  override def findById(id: Int): Employee = {
    entityManager.unwrap(classOf[Session])
      .get(classOf[Employee], id)
  }

  override def save(employee: Employee): Unit = {
    entityManager.unwrap(classOf[Session])
      .saveOrUpdate(employee)
  }

  override def deleteById(id: Int): Unit = {
    entityManager.unwrap(classOf[Session])
      .createQuery("delete from Employee where id=:employeeId")
      .setParameter("employeeId", id)
      .executeUpdate()
  }
}
