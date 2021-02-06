package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.hibernatewrapper.SessionFactoryWrapper.getSessionFactoryWrapper

//noinspection Duplicates
object DeleteInstructorDetailDemo {
  def main(args: Array[String]): Unit = {
    getSessionFactoryWrapper(Array(classOf[Instructor], classOf[InstructorDetail])) match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val theId = 4
          val tempInstructorDetail = session.get(classOf[InstructorDetail], theId)

          println(s"tempInstructorDetail: $tempInstructorDetail")
          println(s"the associated instructor: ${tempInstructorDetail.getInstructor}")

          println(s"Deleting tempInstructorDetail: $tempInstructorDetail")
          /*
           * Break the link between Instructor -> InstructorDetail to avoid error
           * "deleted error would be re-saved by cascade
           * (remove deleted object from association)"
           */
          tempInstructorDetail.getInstructor.setInstructorDetail(null)
          session.delete(tempInstructorDetail)
        }
        sessionFactory.close()
    }
  }
}
