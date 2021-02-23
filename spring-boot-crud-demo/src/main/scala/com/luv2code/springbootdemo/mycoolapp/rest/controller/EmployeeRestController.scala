package com.luv2code.springbootdemo.mycoolapp.rest.controller

import com.luv2code.springbootdemo.mycoolapp.rest.dao.EmployeeDAO
import com.luv2code.springbootdemo.mycoolapp.rest.entity.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, RestController}

import java.util.{List => JList}

@RestController
class EmployeeRestController {
  // Refactor later
  @Autowired
  private var employeeDAO: EmployeeDAO = _

  @GetMapping(Array("/employees"))
  def findAll(): JList[Employee] =
    employeeDAO.findAll()
}
