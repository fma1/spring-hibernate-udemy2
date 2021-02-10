package com.luv2code.hibernate.demo.entity

import java.util.{ArrayList => JArrayList, List => JList}
import javax.persistence._
import scala.beans.BeanProperty

@Entity
@Table(name = "student")
class Student(_firstName: String, _lastName: String, _email: String) {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  var id: Int = _

  @BeanProperty
  @Column(name = "first_name")
  var firstName: String = _firstName

  @BeanProperty
  @Column(name = "last_name")
  var lastName: String = _lastName

  @BeanProperty
  @Column(name = "email")
  var email: String = _email

  @BeanProperty
  @ManyToMany(fetch = FetchType.LAZY,
    cascade = Array(CascadeType.PERSIST,
      CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH))
  @JoinTable(
    name = "course_student",
    joinColumns = Array(new JoinColumn(name = "student_id")),
    inverseJoinColumns = Array(new JoinColumn(name = "course_id"))
  )
  var courses: JList[Course] = new JArrayList[Course]()

  private def this() = this(null, null, null)

  override def toString: String = {
    val builder = new StringBuilder
    builder.append("Student(")
    builder.append(s"id: $id, ")
    builder.append(s"firstName: $firstName, ")
    builder.append(s"lastName: $lastName, ")
    builder.append(s"email: $email)")
    builder.toString()
  }
}

object Student {
  def apply(_firstName: String, _lastName: String, _email: String) =
    new Student(_firstName, _lastName, _email)
}
