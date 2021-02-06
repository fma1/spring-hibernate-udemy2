package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.hibernatewrapper.SessionFactoryWrapper.getSessionFactoryWrapper

//noinspection Duplicates
object DeleteInstructorDetailDemo {
  def main(args: Array[String]): Unit = {
    getSessionFactoryWrapper(Array(classOf[Instructor], classOf[InstructorDetail])) match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val theId = 3
          val tempInstructorDetail = session.get(classOf[InstructorDetail], theId)

          println(s"tempInstructorDetail: $tempInstructorDetail")
          println(s"the associated instructor: ${tempInstructorDetail.getInstructor}")

          println(s"Deleting tempInstructorDetail: $tempInstructorDetail")
          session.delete(tempInstructorDetail)
        }
        sessionFactory.close()
    }
  }
}
