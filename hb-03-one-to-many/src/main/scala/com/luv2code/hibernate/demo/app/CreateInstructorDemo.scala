package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.hibernatewrapper.SessionFactoryWrapper

//noinspection Duplicates
object CreateInstructorDemo {
  def main(args: Array[String]): Unit = {
    SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Instructor], classOf[InstructorDetail], classOf[Course]))
    match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val tempInstructor = Instructor("Susan", "Public", "susan.public@luv2code.com")
          val tempInstructorDetail = InstructorDetail("http://www.youtube.com/", "Video Games")
          tempInstructor.setInstructorDetail(tempInstructorDetail)
          println(s"Saving instructor $tempInstructor")
          session.save(tempInstructor)
        }
        sessionFactory.close()
    }
  }
}
