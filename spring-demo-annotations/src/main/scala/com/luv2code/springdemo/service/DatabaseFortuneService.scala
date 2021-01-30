package com.luv2code.springdemo.service

import org.springframework.stereotype.Service

@Service
class DatabaseFortuneService extends FortuneService {
  override def getDailyFortune: String = ""
}
