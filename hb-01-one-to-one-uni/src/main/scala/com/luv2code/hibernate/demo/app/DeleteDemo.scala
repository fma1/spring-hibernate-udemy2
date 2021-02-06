package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.{Instructor, InstructorDetail}
import org.hibernatewrapper.SessionFactoryWrapper

object DeleteDemo {
  def main(args: Array[String]): Unit = {
    SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Instructor], classOf[InstructorDetail]))
    match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val theId = 1

          Option(session.get(classOf[Instructor], theId)).foreach(tempInstructor => {
            println(s"Found instructor $tempInstructor")
            println(s"Deleting instructor $tempInstructor")
            session.delete(tempInstructor)
          })
        }
        sessionFactory.close()
    }
  }
}
