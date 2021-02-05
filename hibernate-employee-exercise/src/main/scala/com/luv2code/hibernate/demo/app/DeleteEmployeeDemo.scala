package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Employee
import org.hibernatewrapper.SessionFactoryWrapper

object DeleteEmployeeDemo {
  def main(args: Array[String]): Unit = {
    SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Employee])) match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val employeeId = 1
          val employee: Employee = session.get(classOf[Employee], employeeId)
          session.delete(employee)
        }
        sessionFactory.close()
    }
  }
}
