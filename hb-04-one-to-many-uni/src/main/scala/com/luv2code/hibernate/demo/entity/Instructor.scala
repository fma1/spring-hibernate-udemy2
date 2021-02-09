package com.luv2code.hibernate.demo.entity

import java.util.{List => JList, ArrayList => JArrayList}
import javax.persistence._
import scala.beans.BeanProperty

@Entity
@Table(name = "instructor")
class Instructor(_firstName: String, _lastName: String, _email: String) {
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
  @OneToOne(cascade = Array(CascadeType.ALL))
  @JoinColumn(name = "instructor_detail_id")
  var instructorDetail: InstructorDetail = _

  @BeanProperty
  // Key for the relationship exists on the Course side
  // Course owns the relationship
  @OneToMany(mappedBy = "instructor",
    cascade = Array(CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH),
    // fetch = FetchType.EAGER)
    fetch = FetchType.LAZY)
  var courses: JList[Course] = new JArrayList[Course]()

  def add(course: Course): Unit = {
    courses.add(course)
    course.setInstructor(this)
  }

  def addAll(courses: List[Course]): Unit = {
    courses.foreach(add)
  }

  private def this() = this(null, null, null)

  override def toString: String = {
    val builder = new StringBuilder
    builder.append("Instructor(")
    builder.append(s"id: $id, ")
    builder.append(s"firstName: $firstName, ")
    builder.append(s"lastName: $lastName, ")
    builder.append(s"email: $email)")
    builder.toString()
  }
}

object Instructor {
  def apply(_firstName: String, _lastName: String, _email: String) =
    new Instructor(_firstName, _lastName, _email)
}