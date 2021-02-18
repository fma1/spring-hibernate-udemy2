package com.luv2code.springdemo.rest.controller

import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(Array("test"))
class DemoRestController {
  @GetMapping(Array("/hello"))
  def sayHello(): String = "Hello World"
}
