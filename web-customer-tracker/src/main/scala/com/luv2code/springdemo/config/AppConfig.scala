package com.luv2code.springdemo.config

import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, ResourceHandlerRegistry, ViewControllerRegistry, WebMvcConfigurer}
import org.springframework.web.servlet.view.InternalResourceViewResolver

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = Array(
  "com.luv2code.springdemo.service",
  "com.luv2code.springdemo.dao",
  "com.luv2code.springdemo.entity"))
class AppConfig extends WebMvcConfigurer {
  @Bean
  def sessionFactory(): LocalSessionFactoryBean = {
    val sessionFactory = new LocalSessionFactoryBean
    sessionFactory.setDataSource(myDataSource())
    sessionFactory.setPackagesToScan("com.luv2code.springdemo.entity")
    sessionFactory.setHibernateProperties(hibernateProperties)
    sessionFactory
  }

  @Bean
  def myDataSource(): ComboPooledDataSource = {
    val dataSource = new ComboPooledDataSource
    dataSource.setDriverClass(classOf[com.mysql.cj.jdbc.Driver].getName)
    dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC")
    dataSource.setUser("springstudent")
    dataSource.setPassword("springstudent")
    dataSource.setMinPoolSize(5)
    dataSource.setMaxPoolSize(20)
    dataSource.setMaxIdleTime(30000)
    dataSource
  }

  @Bean
  def hibernateTransactionManager: PlatformTransactionManager = {
    val transactionManager = new HibernateTransactionManager
    transactionManager.setSessionFactory(sessionFactory().getObject)
    transactionManager
  }

  @Bean
  def sessionFactoryWrapper = new SessionFactoryWrapper(sessionFactory().getObject)

  private def hibernateProperties = {
    val hibernateProperties = new Properties
    hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
    hibernateProperties.setProperty("show-sql", "true")
    hibernateProperties
  }

  override def configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer): Unit = {
    configurer.enable()
  }

  override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
    registry
      .addResourceHandler("/static/**")
      .addResourceLocations("classpath:/static/")
  }

  @Bean
  def viewResolver: InternalResourceViewResolver = {
    val viewResolver = new InternalResourceViewResolver()
    viewResolver.setPrefix("/WEB-INF/view/")
    viewResolver.setSuffix(".jsp")
    viewResolver
  }

  override def addViewControllers(registry: ViewControllerRegistry): Unit = {
    registry.addViewController("/").setViewName("index")
  }
}
