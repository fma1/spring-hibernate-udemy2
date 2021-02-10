package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.app.DeletePacmanCourseDemo.logger
import com.luv2code.hibernate.demo.entity.{Course, Instructor, InstructorDetail, Review, Student}
import org.hibernatewrapper.SessionFactoryWrapper
import org.slf4j.{Logger, LoggerFactory}

//noinspection Duplicates
object DeleteStudentDemo {
  val logger: Logger = LoggerFactory.getLogger(classOf[GetCoursesForMaryDemo])

  def main(args: Array[String]): Unit = {
    val clazzAry: Array[Class[_]] = Array(classOf[Instructor], classOf[InstructorDetail],
      classOf[Course], classOf[Review], classOf[Student])
    SessionFactoryWrapper.getSessionFactoryWrapper(clazzAry) match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val studentId = 2
          val student = session.get(classOf[Student], studentId)

          logger.info(s"Loaded student $student")
          logger.info("Deleting student...")
          session.delete(student)
        }
        logger.info("Done!")
        sessionFactory.close()
    }
  }
}

class DeleteStudentDemo {}