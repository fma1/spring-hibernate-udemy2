package com.luv2code.springbootdemo.mycoolapp.rest.service

import com.luv2code.springbootdemo.mycoolapp.rest.dao.EmployeeDAO
import com.luv2code.springbootdemo.mycoolapp.rest.entity.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.{List => JList}

@Service
class EmployeeService {
   @Autowired
   private var employeeDAO: EmployeeDAO = _

  @Transactional
   def findAll(): JList[Employee] = employeeDAO.findAll()

  @Transactional
   def findById(id: Int): Employee = employeeDAO.findById(id)

  @Transactional
   def save(employee: Employee): Unit = employeeDAO.save(employee)

  @Transactional
   def deleteById(id: Int): Unit = employeeDAO.deleteById(id)
}
