package com.luv2code.springdemo.config

import org.springframework.context.annotation.{ComponentScan, Configuration}

@Configuration
@ComponentScan(basePackages = Array("com.luv2code.springdemo"))
class AppConfig {

}
