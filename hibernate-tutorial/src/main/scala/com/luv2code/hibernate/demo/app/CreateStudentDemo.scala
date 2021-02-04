package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Student
import org.hibernatewrapper.SessionFactoryWrapper

//noinspection DuplicatedCode
object CreateStudentDemo {
  def main(args: Array[String]): Unit = {
    SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Student])) match {
      case (sfw, factory) =>
        sfw.withTransaction() { session =>
          val tempStudent = new Student("Paul", "Wall", "paul@luv2code.com")
          session.save(tempStudent)
        }

        factory.close()
    }

  }
}
