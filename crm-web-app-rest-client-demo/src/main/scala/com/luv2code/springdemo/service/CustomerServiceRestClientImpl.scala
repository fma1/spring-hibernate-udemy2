package com.luv2code.springdemo.service
import com.luv2code.springdemo.entity.Customer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.core.env.Environment
import org.springframework.http.{HttpMethod, ResponseEntity}
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

import java.util.{List => JList}
import javax.annotation.PostConstruct

@Service
class CustomerServiceRestClientImpl extends CustomerService {
  final val logger = LoggerFactory.getLogger(classOf[CustomerServiceRestClientImpl])

  @Autowired
  private var restTemplate: RestTemplate = _

  @Autowired
  private var env: Environment = _

  private var crmRestUrl: String = _

  @PostConstruct
  private def postConstruct(): Unit = {
    crmRestUrl = env.getProperty("crm.rest.url")
    logger.info(s"Loaded property: crm.rest.url=$crmRestUrl")
  }

  override def getCustomerById(customerId: Int): Customer = {
    val newCrmRestUrl = s"$crmRestUrl/$customerId"

    logger.info(s"in getCustomerById(): Calling REST API $newCrmRestUrl")

    val customer =
      restTemplate.getForObject(newCrmRestUrl, classOf[Customer])

    logger.info(s"in getCustomerById(): customer $customer")

    customer
  }

  override def getCustomers: JList[Customer] = {
    logger.info(s"in getCustomers(): Calling REST API $crmRestUrl")

    val responseEntity: ResponseEntity[JList[Customer]] =
      restTemplate.exchange(crmRestUrl,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference[JList[Customer]]() {})

    val customers = responseEntity.getBody

    logger.info(s"in getCustomers(): customers $customers")

    customers
  }

  override def saveCustomer(customer: Customer): Unit = {
    logger.info(s"in saveCustomer(): Calling REST API $crmRestUrl")

    if (customer.id == 0) {
      restTemplate.postForEntity(crmRestUrl, customer, classOf[String])
    } else {
      restTemplate.put(crmRestUrl, classOf[Customer])
    }

    logger.info(s"in saveCustomer(): success")
  }

  override def deleteCustomer(customerId: Int): Unit = {
    val newCrmRestUrl = s"$crmRestUrl/$customerId"

    logger.info(s"in deleteCustomer(): Calling REST API $crmRestUrl")

    restTemplate.delete(newCrmRestUrl)

    logger.info(s"in deleteCustomer(): deleted customer where customerId=$customerId")
  }
}
