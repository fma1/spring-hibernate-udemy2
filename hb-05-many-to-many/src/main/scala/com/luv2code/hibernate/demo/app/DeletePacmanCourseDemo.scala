package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.{Course, Instructor, InstructorDetail, Review, Student}
import org.hibernatewrapper.SessionFactoryWrapper
import org.slf4j.{Logger, LoggerFactory}

//noinspection Duplicates
object DeletePacmanCourseDemo {
  val logger: Logger = LoggerFactory.getLogger(classOf[GetCoursesForMaryDemo])

  def main(args: Array[String]): Unit = {
    val clazzAry: Array[Class[_]] = Array(classOf[Instructor], classOf[InstructorDetail],
      classOf[Course], classOf[Review], classOf[Student])
    SessionFactoryWrapper.getSessionFactoryWrapper(clazzAry) match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val courseId = 10
          val course = session.get(classOf[Course], courseId)

          logger.info(s"Loaded course $Course")
          logger.info("Deleting course...")
          session.delete(course)
        }
        logger.info("Done!")
        sessionFactory.close()
    }
  }
}

class DeletePacmanCourseDemo {}