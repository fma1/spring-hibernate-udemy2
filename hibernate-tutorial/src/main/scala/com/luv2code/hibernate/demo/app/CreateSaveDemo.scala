package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Student
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.hibernatewrapper.SessionFactoryWrapper

object CreateSaveDemo {
  def main(args: Array[String]): Unit = {
    val factory: SessionFactory = new Configuration()
      .configure("hibernate.cfg.xml")
      .addAnnotatedClass(classOf[Student])
      .buildSessionFactory()
    val sfw = new SessionFactoryWrapper(factory)

    sfw.withTransaction() { session =>
      val tempStudent = new Student("Paul", "Wall", "paul@luv2code.com")
      session.save(tempStudent)
    }

    factory.close()
  }
}
