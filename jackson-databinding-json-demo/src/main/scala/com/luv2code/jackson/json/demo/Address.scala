package com.luv2code.jackson.json.demo

import scala.runtime.ScalaRunTime

case class Address(street: String, city: String, state: String, zip: String, country: String) {
  override def toString: String = ScalaRunTime._toString(this)
}
