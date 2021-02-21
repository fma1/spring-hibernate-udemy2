package com.luv2code.springdemo.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.mchange.v2.c3p0.ComboPooledDataSource
import org.hibernatewrapper.SessionFactoryWrapper
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.orm.hibernate5.{HibernateTransactionManager, LocalSessionFactoryBean}
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.config.annotation.{DefaultServletHandlerConfigurer, EnableWebMvc, ResourceHandlerRegistry, ViewControllerRegistry, WebMvcConfigurer}
import org.springframework.web.servlet.view.InternalResourceViewResolver

import java.util.{List => JList}

@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = Array("com.luv2code.springdemo.*"))
@PropertySource(Array("classpath:application.properties"))
class AppConfig extends WebMvcConfigurer {
  @Bean
  def restTemplate() = new RestTemplate()

  @Bean
  def objectMapper(): ObjectMapper = {
    val objectMapper = new ObjectMapper()
    objectMapper.registerModule(DefaultScalaModule)
    objectMapper
  }

  @Bean
  def mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter = {
    val mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(objectMapper())
    mappingJackson2HttpMessageConverter
  }

  override def configureMessageConverters(converters: JList[HttpMessageConverter[_]]): Unit = {
    converters.add(mappingJackson2HttpMessageConverter())
  }
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
    val hibernateProperties = new java.util.Properties
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
