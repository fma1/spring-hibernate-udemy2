package com.luv2code.springdemo

import com.luv2code.springdemo.config.AppConfig
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object SpringDemoApp {
  def main(args: Array[String]): Unit = {
    val appContext = new AnnotationConfigApplicationContext(classOf[AppConfig])
    val coach = appContext.getBean("tennisCoach", classOf[Coach])
    println(coach.getDailyWorkout)
    appContext.close()
  }
}
