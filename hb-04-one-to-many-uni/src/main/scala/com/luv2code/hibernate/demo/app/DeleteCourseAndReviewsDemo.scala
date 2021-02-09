package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.hibernatewrapper.SessionFactoryWrapper

//noinspection Duplicates
object DeleteCourseAndReviewsDemo {
  def main(args: Array[String]): Unit = {
    val clazzAry: Array[Class[_]] = Array(classOf[Instructor],
      classOf[InstructorDetail], classOf[Course], classOf[Review])
    SessionFactoryWrapper.getSessionFactoryWrapper(clazzAry) match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val theId = 11
          val course = session.get(classOf[Course], theId)
          println(s"Deleting course: $course")
          session.delete(course)
          println("Done!")
        }
        sessionFactory.close()
    }
  }
}
