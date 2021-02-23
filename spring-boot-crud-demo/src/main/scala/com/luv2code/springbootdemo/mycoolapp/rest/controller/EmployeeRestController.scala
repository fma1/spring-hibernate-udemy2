package com.luv2code.springbootdemo.mycoolapp.rest.controller

import com.luv2code.springbootdemo.mycoolapp.rest.entity.Employee
import com.luv2code.springbootdemo.mycoolapp.rest.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{DeleteMapping, GetMapping, PathVariable, PostMapping, PutMapping, RequestBody, RestController}

import java.util.{List => JList}

@RestController
class EmployeeRestController {
  @Autowired
  private var employeeService: EmployeeService = _

  @GetMapping(Array("/employees"))
  def findAll(): JList[Employee] =
    employeeService.findAll()

  @GetMapping(Array("/employees/{employeeId}"))
  def findById(@PathVariable employeeId: Int): Employee = {
    Option(employeeService.findById(employeeId)) match {
      case None =>
        throw new RuntimeException(s"Employee not found - $employeeId")
      case Some(employee) =>
        employee
    }
  }

  @PostMapping(Array("/employees"))
  def addEmployee(@RequestBody employee: Employee): Employee = {
    employee.id = 0
    employeeService.save(employee)
    employee
  }

  @PutMapping(Array("/employees"))
  def updateEmployee(@RequestBody employee: Employee): Employee = {
    employeeService.save(employee)
    employee
  }

  @DeleteMapping(Array("/employees/{employeeId}"))
  def deleteById(@PathVariable employeeId: Int): String = {
    Option(employeeService.findById(employeeId)) match {
      case None =>
        throw new RuntimeException(s"Employee not found - $employeeId")
      case Some(_) =>
        employeeService.deleteById(employeeId)
        s"Deleted employee - $employeeId"
    }
  }
}
