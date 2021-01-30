package com.luv2code.springdemo.service

import org.springframework.stereotype.Service

@Service
class HappyFortuneService extends FortuneService {
  override def getDailyFortune: String = "Today is your lucky day!"
}
