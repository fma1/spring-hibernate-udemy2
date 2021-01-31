package com.luv2code.springdemo.config

import com.luv2code.springdemo.SwimCoach
import com.luv2code.springdemo.service.{FortuneService, HappyFortuneService}
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}

@Configuration
@ComponentScan(basePackages = Array("com.luv2code.springdemo"))
class AppConfig {
  @Bean
  def sadFortuneService(): FortuneService = new HappyFortuneService()

  @Bean
  def swimCoach(): SwimCoach = new SwimCoach(sadFortuneService())
}
