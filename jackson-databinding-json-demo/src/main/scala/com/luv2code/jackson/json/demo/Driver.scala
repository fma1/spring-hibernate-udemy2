package com.luv2code.jackson.json.demo

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object Driver {
  val mapper: JsonMapper = JsonMapper.builder()
    .addModule(DefaultScalaModule)
    .build()

  def main(args: Array[String]): Unit = {
    val content = getClass.getClassLoader.getResource("sample-full.json")
    val student = mapper.readValue(content, new TypeReference[Student]{})
    println(s"Student First Name: ${student.firstName}")
    println(s"Student Last Name: ${student.lastName}")
    println(s"Student Address: ${student.address}")
    println(s"Student Languages: ${student.languages.mkString("Array(", ", ", ")")}")
  }
}
