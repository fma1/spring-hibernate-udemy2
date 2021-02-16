package com.luv2code.springsecurity.demo.config

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.hibernatewrapper.SessionFactoryWrapper
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}
import org.springframework.core.env.Environment
import org.springframework.orm.hibernate5.{HibernateTransactionManager, LocalSessionFactoryBean}
import org.springframework.transaction.PlatformTransactionManager
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

    // Just to confirm we're reading the data from the properties file
    // Would probably want to remove in a production environment
    logger.info(s">>> jdbc.url=${env.getProperty(JDBC_URL)}")
    logger.info(s">>> jdbc.user=${env.getProperty(JDBC_USER)}")

    dataSource.setDriverClass(env.getProperty(JDBC_DRIVER))
    dataSource.setJdbcUrl(env.getProperty(JDBC_URL))
    dataSource.setUser(env.getProperty(JDBC_USER))
    dataSource.setPassword(env.getProperty(JDBC_PASSWORD))
    dataSource.setInitialPoolSize(env.getProperty(CONNECTION_POOL_INITIAL_POOL_SIZE).toInt)
    dataSource.setMinPoolSize(env.getProperty(CONNECTION_POOL_MIN_POOL_SIZE).toInt)
    dataSource.setMaxPoolSize(env.getProperty(CONNECTION_POOL_MAX_POOL_SIZE).toInt)
    dataSource.setMaxIdleTime(env.getProperty(CONNECTION_POOL_MAX_IDLE_TIME).toInt)
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
