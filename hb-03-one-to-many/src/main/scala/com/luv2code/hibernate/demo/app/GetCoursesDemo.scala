package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.hibernatewrapper.SessionFactoryWrapper

import scala.jdk.CollectionConverters._

//noinspection Duplicates
object GetCoursesDemo {
  def main(args: Array[String]): Unit = {
    SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Instructor], classOf[InstructorDetail], classOf[Course]))
    match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val theId = 1
          val instructor = session.get(classOf[Instructor], theId)
          println(s"Instructor: $instructor")
          println(s"Courses: ${instructor.getCourses.asScala.mkString(", ")}")
        }
        sessionFactory.close()
    }
  }
}
