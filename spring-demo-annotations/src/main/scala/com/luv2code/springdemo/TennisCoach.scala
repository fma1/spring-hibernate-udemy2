package com.luv2code.springdemo

import com.luv2code.springdemo.service.FortuneService
import org.springframework.beans.factory.annotation.{Autowired, Qualifier}
import org.springframework.stereotype.Component

import scala.beans.BeanProperty

@Component
class TennisCoach extends Coach {
  @Autowired
  @BeanProperty
  @Qualifier("happyFortuneService")
  var fortuneService: FortuneService = _

  override def getDailyWorkout: String = "Do ten pushups."
  override def getDailyFortune: String = fortuneService.getDailyFortune

  /*
  @Autowired
  def doSomeCrazyStuff(fortuneService: FortuneService): Unit = {
    println("Doing some crazy stuff!")
    this.fortuneService = fortuneService
  }
   */
}
