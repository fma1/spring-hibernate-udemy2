package com.luv2code.springdemo

import com.luv2code.springdemo.config.AppConfig
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object AnnotationDemoApp {
  def main(args: Array[String]): Unit = {
    val appContext = new AnnotationConfigApplicationContext(classOf[AppConfig])
    scala.sys.addShutdownHook({appContext.close()})
    // val coach = appContext.getBean("tennisCoach", classOf[Coach])
    val coach = appContext.getBean("swimCoach", classOf[SwimCoach])
    println(coach.getDailyWorkout)
    println(coach.getDailyFortune)
    println(s"email: ${coach.getEmail}")
    println(s"team: ${coach.getTeam}")
  }
}
