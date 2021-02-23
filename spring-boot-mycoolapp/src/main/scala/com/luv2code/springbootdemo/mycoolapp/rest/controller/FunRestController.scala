package com.luv2code.springbootdemo.mycoolapp.rest.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.{GetMapping, RestController}

import java.time.LocalDateTime
import javax.annotation.PostConstruct

@RestController
class FunRestController {
  private var coachName: String = _
  private var teamName: String = _

  @Autowired
  private var env: Environment = _

  @PostConstruct
  private def postConstruct(): Unit = {
    coachName = env.getProperty("coach.name")
    teamName = env.getProperty("team.name")
  }

  @GetMapping(Array("/"))
  def sayHello: String =
    s"""Hello World 2! Time on server is now ${LocalDateTime.now()}<br />
       |Coach name: $coachName<br />
       |Team name: $teamName<br />
       |""".stripMargin
}
