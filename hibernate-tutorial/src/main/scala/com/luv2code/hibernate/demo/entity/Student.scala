package com.luv2code.hibernate.demo.entity

import org.hibernatewrapper.util.HBUtil

import javax.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, Table}
import scala.beans.BeanProperty

@Entity
@Table(name = "student")
class Student(_firstName: String, _lastName: String, _email: String) {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  var id: Long = _

  @BeanProperty
  @Column(name = "first_name")
  var firstName: String = _firstName

  @BeanProperty
  @Column(name = "last_name")
  var lastName: String = _lastName

  @BeanProperty
  @Column(name = "email")
  var email: String = _email

  private def this() = this(null, null, null)

  override def toString: String = {
    HBUtil.reflectToString(this)
  }
}

object Student {
  def apply(firstName: String, lastName: String, email: String) = new Student(firstName, lastName, email)
}