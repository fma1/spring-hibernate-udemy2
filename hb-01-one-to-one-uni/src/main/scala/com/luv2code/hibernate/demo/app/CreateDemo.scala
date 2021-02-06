package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.hibernatewrapper.SessionFactoryWrapper

object CreateDemo {
  def main(args: Array[String]): Unit = {
    SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Instructor], classOf[InstructorDetail]))
    match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val tempInstructor = Instructor("Chad", "Darby", "darby@luv2code.com")
          val tempInstructorDetail = InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!")
          tempInstructor.setInstructorDetail(tempInstructorDetail)
          println(s"Saving instructor $tempInstructor")
          session.save(tempInstructor)
        }
        sessionFactory.close()
    }
  }
}
