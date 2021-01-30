package com.luv2code.springdemo

import com.luv2code.springdemo.service.FortuneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TennisCoach(@Autowired var fortuneService: FortuneService) extends Coach {
  override def getDailyWorkout: String = "Do ten pushups."
  override def getDailyFortune: String = fortuneService.getDailyFortune
}
