package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.hibernatewrapper.SessionFactoryWrapper
import org.slf4j.{Logger, LoggerFactory}

//noinspection Duplicates
object CreateCoursesAndStudentsDemo {
  val logger: Logger = LoggerFactory.getLogger(classOf[CreateCoursesAndStudentsDemo])

  def main(args: Array[String]): Unit = {
    val clazzAry: Array[Class[_]] = Array(classOf[Instructor], classOf[InstructorDetail],
      classOf[Course], classOf[Review], classOf[Student])
    SessionFactoryWrapper.getSessionFactoryWrapper(clazzAry) match {
      case (sfw, sessionFactory) =>
        sfw.withTransaction() { session =>
          val course = Course("Pacman - How to Score One Million Points")
          logger.info("Saving the course...")
          session.save(course)
          logger.info(s"Saved the course: $course...")

          val studentLst = List(Student("John", "Doe", "john@luv2code.com"),
            Student("Mary", "Public", "mary@luv2code.com"))
          course.addAllStudents(studentLst)

          logger.info("\nSaving students...")
          studentLst.foreach(session.save)
          logger.info(s"Saved students: ${studentLst.mkString(", ")}")
        }
        logger.info("Done!")
        sessionFactory.close()
    }
  }
}

class CreateCoursesAndStudentsDemo {}