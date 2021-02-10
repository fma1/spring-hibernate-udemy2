package com.luv2code.hibernate.demo.entity

import org.hibernatewrapper.util.HBUtil

import javax.persistence._
import scala.beans.BeanProperty

@Entity
@Table(name = "review")
class Review(_comment: String) {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  var id: Int = _

  @BeanProperty
  @Column(name = "comment")
  var comment: String = _comment

  private def this() = this(null)

  override def toString: String = {
    HBUtil.reflectToString(this)
  }
}

object Review {
  def apply(_comment: String) = new Review(_comment)
}
