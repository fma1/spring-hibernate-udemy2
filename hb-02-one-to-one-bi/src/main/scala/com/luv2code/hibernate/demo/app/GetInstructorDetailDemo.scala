package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.hibernatewrapper.SessionFactoryWrapper.getSessionFactoryWrapper

//noinspection Duplicates
object GetInstructorDetailDemo {
  def main(args: Array[String]): Unit = {
    getSessionFactoryWrapper(Array(classOf[Instructor], classOf[InstructorDetail])) match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val theId = 3
          val tempInstructorDetail = session.get(classOf[InstructorDetail], theId)

          println(s"tempInstructorDetail: $tempInstructorDetail")
          println(s"the associated instructor: ${tempInstructorDetail.getInstructor}")
        }
        sessionFactory.close()
    }
  }
}
