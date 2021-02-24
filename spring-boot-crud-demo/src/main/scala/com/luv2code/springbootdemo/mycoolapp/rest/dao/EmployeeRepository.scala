package com.luv2code.springbootdemo.mycoolapp.rest.dao

import com.luv2code.springbootdemo.mycoolapp.rest.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository

trait EmployeeRepository extends JpaRepository[Employee, Int] {

}
