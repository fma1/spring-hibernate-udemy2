package com.luv2code.springdemo.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.mchange.v2.c3p0.ComboPooledDataSource
import org.hibernatewrapper.SessionFactoryWrapper
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}
import org.springframework.core.env.Environment
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.orm.hibernate5.{HibernateTransactionManager, LocalSessionFactoryBean}
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.config.annotation.{DefaultServletHandlerConfigurer, EnableWebMvc, ResourceHandlerRegistry, ViewControllerRegistry, WebMvcConfigurer}
import org.springframework.web.servlet.view.InternalResourceViewResolver

import java.util

@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = Array("com.luv2code.springdemo.*"))
@PropertySource(value = Array("classpath:persistence-mysql.properties"))
class AppConfig extends WebMvcConfigurer {
  final val logger: Logger = LoggerFactory.getLogger(classOf[AppConfig])

  final val JDBC_DRIVER: String = "jdbc.driver"
  final val JDBC_URL: String = "jdbc.url"
  final val JDBC_USER: String = "jdbc.user"
  final val JDBC_PASSWORD: String = "jdbc.password"
  final val CONNECTION_POOL_INITIAL_POOL_SIZE: String = "connection.pool.initialPoolSize"
  final val CONNECTION_POOL_MIN_POOL_SIZE: String = "connection.pool.minPoolSize"
  final val CONNECTION_POOL_MAX_POOL_SIZE: String = "connection.pool.maxPoolSize"
  final val CONNECTION_POOL_MAX_IDLE_TIME: String = "connection.pool.maxIdleTime"
  final val HIBERNATE_PROP_KEYS: Array[String] =
    Array("hibernate.dialect", "hibernate.show_sql", "hibernate.packagesToScan")

  @Autowired
  private var env: Environment = _

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

  override def configureMessageConverters(converters: util.List[HttpMessageConverter[_]]): Unit = {
    converters.add(mappingJackson2HttpMessageConverter())
  }

  @Bean
  def sessionFactory(): LocalSessionFactoryBean = {
    val sessionFactory = new LocalSessionFactoryBean
    sessionFactory.setDataSource(dataSource())
    sessionFactory.setPackagesToScan("com.luv2code.springdemo.entity")
    sessionFactory.setHibernateProperties(hibernateProperties)
    sessionFactory
  }

  @Bean
  def dataSource(): ComboPooledDataSource = {
    val securityDataSource = new ComboPooledDataSource

    // Just to confirm we're reading the data from the properties file
    // Would probably want to remove in a production environment
    logger.info(s">>> jdbc.url=${env.getProperty(JDBC_URL)}")
    logger.info(s">>> jdbc.user=${env.getProperty(JDBC_USER)}")

    securityDataSource.setDriverClass(env.getProperty(JDBC_DRIVER))
    securityDataSource.setJdbcUrl(env.getProperty(JDBC_URL))
    securityDataSource.setUser(env.getProperty(JDBC_USER))
    securityDataSource.setPassword(env.getProperty(JDBC_PASSWORD))
    securityDataSource.setInitialPoolSize(env.getProperty(CONNECTION_POOL_INITIAL_POOL_SIZE).toInt)
    securityDataSource.setMinPoolSize(env.getProperty(CONNECTION_POOL_MIN_POOL_SIZE).toInt)
    securityDataSource.setMaxPoolSize(env.getProperty(CONNECTION_POOL_MAX_POOL_SIZE).toInt)
    securityDataSource.setMaxIdleTime(env.getProperty(CONNECTION_POOL_MAX_IDLE_TIME).toInt)
    securityDataSource
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
    HIBERNATE_PROP_KEYS.foreach(key =>
      Option(env.getProperty(key))
        .foreach(value => hibernateProperties.setProperty(key, value)))
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
