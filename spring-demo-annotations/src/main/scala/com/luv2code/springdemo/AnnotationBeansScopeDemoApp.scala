package com.luv2code.springdemo

import com.luv2code.springdemo.config.AppConfig
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.core.config.Configurator
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object AnnotationBeansScopeDemoApp {
  val logger: Logger = LogManager.getLogger(classOf[AnnotationBeansScopeDemoApp])

  Configurator.initialize(null, "D:\\Documents\\IdeaProjects\\spring-hibernate-udemy2\\spring-demo-annotations\\src\\main\\resources\\log4j2.xml")

  def main(args: Array[String]): Unit = {
    logger.info("test")
    val appContext = new AnnotationConfigApplicationContext(classOf[AppConfig])
    val theCoach = appContext.getBean("tennisCoach", classOf[Coach])
    val alphaCoach = appContext.getBean("tennisCoach", classOf[Coach])
    println(s"Pointing to the same object: ${theCoach == alphaCoach}")
    println(s"hashCode for theCoach: $theCoach")
    println(s"hashCode for alphaCoach: $alphaCoach")
    appContext.close()
  }
}

class AnnotationBeansScopeDemoApp  {}

