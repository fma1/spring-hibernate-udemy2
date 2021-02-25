package com.luv2code.springbootdemo.mycoolapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

object SpringDataRestCrudDemoApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[SpringDataRestCrudDemoApplication])
  }
}

@SpringBootApplication
class SpringDataRestCrudDemoApplication
