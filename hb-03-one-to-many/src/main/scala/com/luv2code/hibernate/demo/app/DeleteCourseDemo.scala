package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.hibernatewrapper.SessionFactoryWrapper

import scala.jdk.CollectionConverters._

//noinspection Duplicates
object DeleteCourseDemo {
  def main(args: Array[String]): Unit = {
    SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Instructor], classOf[InstructorDetail], classOf[Course]))
    match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val theId = 10
          val course = session.get(classOf[Course], theId)
          println(s"Deleting course: $course")
          session.delete(course)
          println("Done!")
        }
        sessionFactory.close()
    }
  }
}
