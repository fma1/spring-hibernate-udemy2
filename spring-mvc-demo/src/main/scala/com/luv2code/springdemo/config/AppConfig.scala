package com.luv2code.springdemo.config

import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, ResourceHandlerRegistry, ViewControllerRegistry, WebMvcConfigurer}
import org.springframework.web.servlet.view.InternalResourceViewResolver

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = Array("com.luv2code.springdemo"))
class AppConfig extends WebMvcConfigurer {
  @Bean
  def viewResolver(): ViewResolver = {
    val viewResolver = new InternalResourceViewResolver()
    viewResolver.setPrefix("/view/")
    viewResolver.setSuffix(".jsp")
    viewResolver
  }

  override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
  }
}
