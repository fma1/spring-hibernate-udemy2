package com.luv2code.springdemo

import com.luv2code.springdemo.service.FortuneService
import org.springframework.beans.factory.annotation.{Autowired, Qualifier}
import org.springframework.stereotype.Component

import javax.annotation.{PostConstruct, PreDestroy}
import scala.beans.BeanProperty

class SwimCoach(fortuneService: FortuneService) extends Coach {
  override def getDailyWorkout: String = "Swim 1000 meters as a warm-up."
  override def getDailyFortune: String = fortuneService.getDailyFortune
}
