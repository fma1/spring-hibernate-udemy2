package com.luv2code.springdemo

import scala.beans.BeanProperty
import scala.runtime.ScalaRunTime

case class Student(@BeanProperty var firstName: String, @BeanProperty var lastName: String) {
  def this() = this(null, null)

  override def toString: String = ScalaRunTime._toString(this)
}
