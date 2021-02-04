package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Student
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.hibernatewrapper.SessionFactoryWrapper

import scala.jdk.CollectionConverters._

object QueryStudentDemo {
  def main(args: Array[String]): Unit = {
    val factory: SessionFactory = new Configuration()
      .configure("hibernate.cfg.xml")
      .addAnnotatedClass(classOf[Student])
      .buildSessionFactory()
    val sfw = new SessionFactoryWrapper(factory)

    sfw.withTransaction() { session =>
      session.createQuery("""from Student s where s.lastName='Doe'""").getResultList.asScala
        .foreach(println)
    }

    factory.close()
  }
}
