package com.luv2code.springdemo.service

import org.springframework.stereotype.Service

@Service
class RESTFortuneService extends FortuneService {
  override def getDailyFortune: String = ""
}
