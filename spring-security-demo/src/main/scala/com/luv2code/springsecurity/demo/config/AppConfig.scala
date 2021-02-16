package com.luv2code.springsecurity.demo.config

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}
import org.springframework.core.env.Environment
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.config.annotation._
import org.springframework.web.servlet.view.InternalResourceViewResolver

@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = Array("com.luv2code.springsecurity.demo.*"))
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

  @Autowired
  private var env: Environment = _

  @Bean
  def securityDataSource(): ComboPooledDataSource = {
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
