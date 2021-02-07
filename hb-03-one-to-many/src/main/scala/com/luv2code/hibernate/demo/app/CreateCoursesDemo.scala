package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.hibernatewrapper.SessionFactoryWrapper

//noinspection Duplicates
object CreateCoursesDemo {
  def main(args: Array[String]): Unit = {
    SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Instructor], classOf[InstructorDetail], classOf[Course]))
    match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val theId = 1
          val instructor = session.get(classOf[Instructor], theId)
          val courseLst = List(Course("Air Guitar"), Course("Pinball Masterclass"))

          instructor.addAll(courseLst)
          courseLst.foreach(session.save)
        }
        sessionFactory.close()
    }
  }
}
