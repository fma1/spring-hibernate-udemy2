package com.luv2code.springdemo

import com.luv2code.springdemo.config.AppConfig
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object AnnotationDemoApp {
  def main(args: Array[String]): Unit = {
    val appContext = new AnnotationConfigApplicationContext(classOf[AppConfig])
    val coach = appContext.getBean("tennisCoach", classOf[Coach])
    println(coach.getDailyWorkout)
    println(coach.getDailyFortune)
    appContext.close()
  }
}
