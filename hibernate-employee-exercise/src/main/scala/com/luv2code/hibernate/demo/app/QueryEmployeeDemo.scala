package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Employee
import org.hibernatewrapper.SessionFactoryWrapper

import scala.jdk.CollectionConverters._

object QueryEmployeeDemo {
  def main(args: Array[String]): Unit = {
    SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Employee])) match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          session.createQuery("""from Employee e where e.firstName = 'John'""")
            .getResultList.asScala.foreach(println)
        }
        sessionFactory.close()
    }
  }
}
