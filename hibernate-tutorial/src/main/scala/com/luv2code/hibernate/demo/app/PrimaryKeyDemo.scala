package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Student
import org.hibernatewrapper.SessionFactoryWrapper

//noinspection DuplicatedCode
object PrimaryKeyDemo {
  def main(args: Array[String]): Unit = {
    SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Student])) match {
      case (sfw, factory) =>
        sfw.withTransaction() { session =>
          val tempStudent1 = new Student("John", "Doe", "paul@luv2code.com")
          val tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com")
          val tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com")
          session.save(tempStudent1)
          session.save(tempStudent2)
          session.save(tempStudent3)
        }

        factory.close()
    }

  }
}
