package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Student
import org.hibernatewrapper.SessionFactoryWrapper

//noinspection DuplicatedCode
object UpdateStudentDemo {
  def main(args: Array[String]): Unit = {
    SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Student])) match {
      case (sfw, factory) =>
        sfw.withTransaction() { session =>
          val studentId = 1L
          val student = session.get(classOf[Student], studentId)
          println(student)
          student.setFirstName("Scooby")

          session.createQuery("""update Student set email='foo@gmail.com'""")
            .executeUpdate()
        }

        factory.close()
    }

  }
}
