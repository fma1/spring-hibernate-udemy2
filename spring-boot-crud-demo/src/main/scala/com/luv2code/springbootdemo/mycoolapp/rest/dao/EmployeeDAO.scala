package com.luv2code.springbootdemo.mycoolapp.rest.dao

import com.luv2code.springbootdemo.mycoolapp.rest.entity.Employee

import java.util.{List => JList}

trait EmployeeDAO {
  def findAll(): JList[Employee]
}
