package com.luv2code.hibernate.demo.entity

import org.hibernatewrapper.util.HBUtil

import java.util.{ArrayList => JArrayList, List => JList}
import javax.persistence._
import scala.beans.BeanProperty
import scala.jdk.CollectionConverters.IterableHasAsJava

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
  @ManyToOne(cascade = Array(CascadeType.PERSIST, CascadeType.MERGE,
                            CascadeType.DETACH, CascadeType.REFRESH))
  @JoinColumn(name = "instructor_id")
  var instructor: Instructor = _

  @BeanProperty
  // CascadeType.DELETE because if a Course is deleted all reviews should be as well
  @OneToMany(fetch = FetchType.LAZY, cascade = Array(CascadeType.ALL))
  @JoinColumn(name = "course_id")
  var reviews: JList[Review] = new JArrayList[Review]()

  @BeanProperty
  @ManyToMany(fetch = FetchType.LAZY,
    cascade = Array(CascadeType.PERSIST,
      CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH))
  @JoinTable(
    name = "course_student",
    joinColumns = Array(new JoinColumn(name = "course_id")),
    inverseJoinColumns = Array(new JoinColumn(name = "student_id"))
  )
  var students: JList[Student] = new JArrayList[Student]()

  def addReview(tempReview: Review): Unit = {
    reviews.add(tempReview)
  }

  def addAllReviews(tempReviews: List[Review]): Unit = {
    reviews.addAll(tempReviews.asJavaCollection)
  }

  def addStudent(tempStudent: Student): Unit = {
    students.add(tempStudent)
  }

  def addAllStudents(tempStudents: List[Student]): Unit = {
    students.addAll(tempStudents.asJavaCollection)
  }

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