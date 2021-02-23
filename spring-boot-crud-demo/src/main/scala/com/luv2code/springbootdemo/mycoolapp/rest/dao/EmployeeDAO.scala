package com.luv2code.springbootdemo.mycoolapp.rest.dao

import com.luv2code.springbootdemo.mycoolapp.rest.entity.Employee

import java.util.{List => JList}

trait EmployeeDAO {
  def findAll(): JList[Employee]
  def findById(id: Int): Employee
  def save(employee: Employee): Unit
  def deleteById(id: Int): Unit
}
