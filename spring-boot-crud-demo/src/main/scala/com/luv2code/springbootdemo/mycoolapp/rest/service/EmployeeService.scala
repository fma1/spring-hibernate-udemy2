package com.luv2code.springbootdemo.mycoolapp.rest.service

import com.luv2code.springbootdemo.mycoolapp.rest.dao.{EmployeeDAO, EmployeeRepository}
import com.luv2code.springbootdemo.mycoolapp.rest.entity.Employee
import org.springframework.beans.factory.annotation.{Autowired, Qualifier}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.{List => JList}

import scala.jdk.OptionConverters._

@Service
class EmployeeService {
  @Autowired
  private var employeeRepository: EmployeeRepository = _

  @Transactional
  def findAll(): JList[Employee] = employeeRepository.findAll()

  @Transactional
  def findById(id: Int): Employee = {
     employeeRepository.findById(id).toScala match {
       case None =>
         throw new RuntimeException(s"Employee not found - $id")
       case Some(value) =>
         value
     }
  }

  @Transactional
  def save(employee: Employee): Unit = employeeRepository.save(employee)

  @Transactional
  def deleteById(id: Int): Unit = employeeRepository.deleteById(id)
}
