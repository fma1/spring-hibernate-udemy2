package com.luv2code.springdemo.config

import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

import javax.servlet.ServletContext

class MyWebAppInitializer extends WebApplicationInitializer {
  override def onStartup(container: ServletContext): Unit = {
    val rootContext = new AnnotationConfigWebApplicationContext()
    rootContext.register(classOf[AppConfig])
    rootContext.setServletContext(container)

    val dispatcher = container.addServlet("dispatcher", new DispatcherServlet(rootContext))
    dispatcher.setLoadOnStartup(1)
    dispatcher.addMapping("/")
  }
}
