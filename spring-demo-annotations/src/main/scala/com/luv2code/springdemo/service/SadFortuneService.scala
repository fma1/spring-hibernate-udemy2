package com.luv2code.springdemo.service

import org.springframework.stereotype.Service

@Service
class SadFortuneService extends FortuneService {
  override def getDailyFortune: String = "Today is a bad day."
}
