package com.luv2code.springdemo

import com.luv2code.springdemo.config.AppConfig
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object AnnotationBeansScopeDemoApp {
  def main(args: Array[String]): Unit = {
    val appContext = new AnnotationConfigApplicationContext(classOf[AppConfig])
    val theCoach = appContext.getBean("tennisCoach", classOf[Coach])
    val alphaCoach = appContext.getBean("tennisCoach", classOf[Coach])
    println(s"Pointing to the same object: ${theCoach == alphaCoach}")
    println(s"hashCode for theCoach: $theCoach")
    println(s"hashCode for alphaCoach: $alphaCoach")
    appContext.close()
  }
}
