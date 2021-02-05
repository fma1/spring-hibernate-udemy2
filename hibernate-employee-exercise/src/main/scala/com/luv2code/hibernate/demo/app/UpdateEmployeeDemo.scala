package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Employee
import org.hibernatewrapper.SessionFactoryWrapper

object UpdateEmployeeDemo {
  def main(args: Array[String]): Unit = {
    SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Employee])) match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val employeeId = 1
          val employee: Employee = session.get(classOf[Employee], employeeId)
          employee.setCompany("Apple")
          session.update(employee)
        }
        sessionFactory.close()
    }
  }
}
