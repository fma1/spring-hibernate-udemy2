package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Student
import org.hibernatewrapper.SessionFactoryWrapper

//noinspection DuplicatedCode
object DeleteStudentDemo {
  def main(args: Array[String]): Unit = {
    SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Student])) match {
      case (sfw, factory) =>
        sfw.withTransaction() { session =>
          session.createQuery("""delete from Student where id=2""")
            .executeUpdate()
        }

        factory.close()
    }

  }
}
