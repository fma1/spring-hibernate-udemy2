package com.luv2code.springsecurity.demo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}

import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private var securityDataSource: DataSource = _

  override def configure(auth: AuthenticationManagerBuilder): Unit = {
    auth.jdbcAuthentication().dataSource(securityDataSource)
  }

  override def configure(http: HttpSecurity): Unit = {
    /*
     * authorizeRequests() -> Restrict access based on HttpServletRequest using RequestMatcher implementations
     * anyRequest().authenticated() -> Restrict access based on authentication/Any request to app must be authenticated
     * formLogin() - Customize form login process
     * ..loginPage() - Show our custom form at mapping "/showMyLoginPage"
     * ..loginProcessingUrl() - Login form should POST data to this URL for processing (to check username and password)
     * ..permitAll() - Let everyone see the login page; no need to be authenticated
     */
    http.authorizeRequests()
      .antMatchers("/").hasRole("EMPLOYEE")
      .antMatchers("/leaders/**").hasRole("MANAGER")
      .antMatchers("/systems/**").hasRole("ADMIN")
      .anyRequest().authenticated()
      .and()
      .formLogin()
        .loginPage("/showMyLoginPage")
        .loginProcessingUrl("/authenticateTheUser")
        .permitAll()
      .and()
      .exceptionHandling()
        .accessDeniedPage("/access-denied")
      .and()
      .logout()
        .permitAll()
  }
}
