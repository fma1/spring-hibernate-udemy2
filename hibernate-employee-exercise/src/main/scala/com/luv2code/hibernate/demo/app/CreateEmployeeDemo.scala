package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Employee
import org.hibernatewrapper.SessionFactoryWrapper

object CreateEmployeeDemo {
  def main(args: Array[String]): Unit = {
    SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Employee])) match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val employee = new Employee("John", "Doe", "Google")
          session.save(employee)
        }
        sessionFactory.close()
    }
  }
}
