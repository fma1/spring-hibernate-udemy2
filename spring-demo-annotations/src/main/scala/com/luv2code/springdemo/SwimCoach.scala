package com.luv2code.springdemo

import com.luv2code.springdemo.service.FortuneService
import org.springframework.beans.factory.annotation.{Autowired, Qualifier, Value}
import org.springframework.stereotype.Component

import javax.annotation.{PostConstruct, PreDestroy}
import scala.beans.BeanProperty

class SwimCoach(fortuneService: FortuneService) extends Coach {
  @Value("${foo.email}")
  private var email: String = _
  @Value("${foo.team}")
  private var team: String = _

  override def getDailyWorkout: String = "Swim 1000 meters as a warm-up."
  override def getDailyFortune: String = fortuneService.getDailyFortune

  def getEmail: String = email
  def getTeam: String = team
}
