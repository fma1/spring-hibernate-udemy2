package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.{Course, Instructor, InstructorDetail, Review, Student}
import org.hibernatewrapper.SessionFactoryWrapper
import org.slf4j.{Logger, LoggerFactory}

//noinspection Duplicates
object AddCourseForMaryDemo {
  val logger: Logger = LoggerFactory.getLogger(classOf[AddCourseForMaryDemo])

  def main(args: Array[String]): Unit = {
    val clazzAry: Array[Class[_]] = Array(classOf[Instructor], classOf[InstructorDetail],
      classOf[Course], classOf[Review], classOf[Student])
    SessionFactoryWrapper.getSessionFactoryWrapper(clazzAry) match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val studentId = 2
          val student = session.get(classOf[Student], studentId)

          val course = Course("Psychology 101")
          course.addStudent(student)

          logger.info("Saving course...")
          session.save(course)
          logger.info(s"Saved course: $course")
        }
        logger.info("Done!")
        sessionFactory.close()
    }
  }
}

class AddCourseForMaryDemo {}