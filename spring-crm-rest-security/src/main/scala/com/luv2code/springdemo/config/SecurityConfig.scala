package com.luv2code.springdemo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User

import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
  override def configure(auth: AuthenticationManagerBuilder): Unit = {
    // auth.jdbcAuthentication().dataSource(securityDataSource)
    val users = User.withDefaultPasswordEncoder()

    auth.inMemoryAuthentication()
      .withUser(users.username("john").password("test123").roles("EMPLOYEE"))
      .withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"))
      .withUser(users.username("susan").password("test123").roles("EMPLOYEE", "ADMIN"))
  }

  override def configure(http: HttpSecurity): Unit = {
    // secures all REST endpoints under "/api/customers"
    http.authorizeRequests()
      .antMatchers("/").hasRole("EMPLOYEE")
      .antMatchers("/leaders/**").hasRole("MANAGER")
      .antMatchers("/systems/**").hasRole("ADMIN")
      .antMatchers(HttpMethod.GET, "/api/customers").hasRole("EMPLOYEE")
      .antMatchers(HttpMethod.GET, "/api/customers/**").hasRole("EMPLOYEE")
      .antMatchers(HttpMethod.POST, "/api/customers").hasAnyRole("MANAGER", "ADMIN")
      .antMatchers(HttpMethod.POST, "/api/customers/**").hasAnyRole("MANAGER", "ADMIN")
      .antMatchers(HttpMethod.PUT, "/api/customers").hasAnyRole("MANAGER", "ADMIN")
      .antMatchers(HttpMethod.PUT, "/api/customers/**").hasAnyRole("MANAGER", "ADMIN")
      .antMatchers(HttpMethod.DELETE, "/api/customers/**").hasRole("ADMIN")
      /*
      .and()
      .formLogin()
        .loginPage("/showMyLoginPage")
        .loginProcessingUrl("/authenticateTheUser")
        .permitAll()
       */
      .and()
      .httpBasic()
      .and()
      .csrf().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

    // Why disable CSRF?
    //
    // Spring Security 5 has CSRF enabled by default. You would need to send over CSRF tokens.
    // However, CSRF generally does not apply for REST APIs. CSRF protection is a request that could be processed by a browser by normal users.
    // If you are only creating a REST service that is used by non-browser clients, you will likely want to disable CSRF protection.
    //
    // For more details, see this link:
    // http://www.tothenew.com/blog/fortifying-your-rest-api-using-spring-security/

    // Why disable sessions?
    //
    // For our application, we would like avoid the use of cookies for sesson tracking. This should force the REST client
    // to enter user name and password for each request. However, this is not always the case depending on the REST client / browser
    // you are using. Your mileage will vary here (for example, this doesn't work in Eclipse embedded browser).
    //
    // For more details, see this link
    // http://www.baeldung.com/spring-security-session
  }
}
