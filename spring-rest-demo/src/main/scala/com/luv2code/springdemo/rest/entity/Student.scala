package com.luv2code.springdemo.rest.entity

import scala.runtime.ScalaRunTime

case class Student(firstName: String, lastName: String) {
  override def toString: String = ScalaRunTime._toString(this)
}
