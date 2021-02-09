package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.hibernatewrapper.SessionFactoryWrapper

//noinspection Duplicates
object CreateCoursesAndReviewsDemo {
  def main(args: Array[String]): Unit = {
    val clazzAry: Array[Class[_]] = Array(classOf[Instructor],
      classOf[InstructorDetail], classOf[Course], classOf[Review])
    SessionFactoryWrapper.getSessionFactoryWrapper(clazzAry) match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val course = Course("Pacman - How to Score One Million Points")
          course.addAllReviews(List(Review("Great course"),
            Review("Cool course"), Review("Dumb Course")))
          println(s"Saving course $course")
          session.save(course)
        }
        sessionFactory.close()
    }
  }
}
