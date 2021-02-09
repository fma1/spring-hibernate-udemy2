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
  @ManyToOne(cascade = Array(CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH))
  @JoinColumn(name = "instructor_id")
  var instructor: Instructor = _

  @BeanProperty
  // CascadeType.DELETE because if a Course is deleted all reviews should be as well
  @OneToMany(cascade = Array(CascadeType.ALL), fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id")
  var reviews: JList[Review] = new JArrayList[Review]()

  def addReview(tempReview: Review): Unit = {
    reviews.add(tempReview)
  }

  def addAllReviews(tempReviews: List[Review]): Unit = {
    reviews.addAll(tempReviews.asJavaCollection)
  }

  private def this() = this(null)

  override def toString: String = {
    HBUtil.reflectToString(this)
  }
}

object Course {
  def apply(_title: String) = new Course(_title)
}