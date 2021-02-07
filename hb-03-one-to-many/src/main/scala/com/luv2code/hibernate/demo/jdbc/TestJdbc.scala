package com.luv2code.hibernate.demo.jdbc

import java.sql.DriverManager

object TestJdbc {
  def main(args: Array[String]): Unit = {
    val jdbcUrl = "jdbc:mysql://localhost:3306/hb-03-one-to-many?use_SSL=false"
    val user = "hbstudent"
    val pass = "hbstudent"

    println(DriverManager.getConnection(jdbcUrl, user, pass))
    println("Connection successful!")
  }
}
