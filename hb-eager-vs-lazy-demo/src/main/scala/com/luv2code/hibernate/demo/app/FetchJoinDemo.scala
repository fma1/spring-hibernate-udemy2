package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.hibernate.query.Query
import org.hibernatewrapper.SessionFactoryWrapper
import org.slf4j.{Logger, LoggerFactory}

import scala.jdk.CollectionConverters._

//noinspection Duplicates
object FetchJoinDemo {
  val logger: Logger = LoggerFactory.getLogger(classOf[FetchJoinDemo])

  def main(args: Array[String]): Unit = {
    val sfwTuple = SessionFactoryWrapper.getSessionFactoryWrapper(Array(classOf[Instructor], classOf[InstructorDetail], classOf[Course]))
    val sessionFactory = sfwTuple._2
    val session = sessionFactory.getCurrentSession

    session.beginTransaction()

    val theId = 1

    val query = session.createQuery(
      """select i from Instructor i
        |JOIN FETCH i.courses
        |where i.id=:theInstructorId
        |""".stripMargin, classOf[Instructor])
    query.setParameter("theInstructorId", theId)

    val tempInstructor = query.getSingleResult

    session.getTransaction.commit()

    session.close()
    logger.info("The session is now closed!")

    sessionFactory.close()
    logger.info("Done!")

    logger.info(s"Associated courses: ${tempInstructor.courses.asScala.mkString(",")}")
 }
}

class FetchJoinDemo {}
