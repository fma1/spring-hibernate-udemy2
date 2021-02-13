package com.luv2code.springdemo.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.{AfterReturning, Aspect, Before, Pointcut}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.stereotype.Component

@Aspect
@Component
class CRMLoggingAspect {
  val logger: Logger = LoggerFactory.getLogger(classOf[CRMLoggingAspect])

  /*
   * First * is for any return type
   * For *.*, First * matches any method, Second * matches any method
   * .. matches any number of arguments
   */
  @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
  def forAppFlow() {}

  @Before("forAppFlow()")
  def beforeAspect(joinPoint: JoinPoint): Unit = {
    System.err.println("TEST")
    logger.info(s"=====>> in Before: calling method: ${joinPoint.getSignature.toShortString}")
    logger.info(s"=====>> in Before: method arguments: (${joinPoint.getArgs.mkString(",")})")
  }

  @AfterReturning("forAppFlow()")
  def afterReturningAspect(joinPoint: JoinPoint): Unit = {
    logger.info(s"=====>> in AfterReturning: calling method: ${joinPoint.getSignature.toShortString}")
  }
}
