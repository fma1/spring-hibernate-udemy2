package com.luv2code.hibernate.demo.entity

import javax.persistence._
import scala.beans.BeanProperty

@Entity
@Table(name = "course")
class Course(_title: String) {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  var id: Int = _

  @BeanProperty
  @Column(name = "title")
  var title: String = _title

  @BeanProperty
  @ManyToOne(cascade = Array(CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH))
  @JoinColumn(name = "instructor_id")
  var instructor: Instructor = _

  private def this() = this(null)

  override def toString: String = {
    val builder = new StringBuilder
    builder.append("Course(")
    builder.append(s"id: $id, ")
    builder.append(s"title: $title)")
    builder.toString()
  }
}

object Course {
  def apply(_title: String) = new Course(_title)
}