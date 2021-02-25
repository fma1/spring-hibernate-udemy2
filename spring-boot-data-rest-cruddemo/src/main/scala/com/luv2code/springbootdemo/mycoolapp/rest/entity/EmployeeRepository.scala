package com.luv2code.springbootdemo.mycoolapp.rest.entity

import org.springframework.data.jpa.repository.JpaRepository

trait EmployeeRepository extends JpaRepository[Employee, java.lang.Integer] {

}
