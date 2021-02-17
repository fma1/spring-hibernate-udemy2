package com.luv2code.jackson.json.demo

import scala.runtime.ScalaRunTime

case class Student(id: Int, firstName: String, lastName: String, active: Boolean, address: Address, languages: Array[String]) {
  override def toString: String = ScalaRunTime._toString(this)
}
