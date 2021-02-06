package com.luv2code.hibernate.demo.entity

import org.hibernatewrapper.util.HBUtil

import javax.persistence._
import scala.beans.BeanProperty

//noinspection DuplicatedCode
@Entity
@Table(name = "instructor_detail")
class InstructorDetail(_youtubeChannel: String, _hobby: String) {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  var id: Int = _

  @BeanProperty
  @Column(name = "youtube_channel")
  var youtubeChannel: String = _youtubeChannel

  @BeanProperty
  @Column(name = "hobby")
  var hobby: String = _hobby

  @BeanProperty
  @OneToOne(mappedBy = "instructorDetail",
    cascade = Array(CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
      CascadeType.REFRESH))
  var instructor: Instructor = _

  private def this() = this(null, null)

  override def toString: String = {
    val builder = new StringBuilder
    builder.append("InstructorDetail(")
    builder.append(s"id: $id, ")
    builder.append(s"youtubeChannel: $youtubeChannel, ")
    builder.append(s"hobby: $hobby)")
    builder.toString()
  }
}

object InstructorDetail {
  def apply(_youtubeChannel: String, _hobby: String) =
    new InstructorDetail(_youtubeChannel, _hobby)
}