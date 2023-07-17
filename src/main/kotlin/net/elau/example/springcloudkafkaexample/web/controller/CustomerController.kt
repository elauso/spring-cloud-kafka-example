package net.elau.example.springcloudkafkaexample.web.controller

import net.elau.example.springcloudkafkaexample.mapper.CustomerMapper
import net.elau.example.springcloudkafkaexample.service.CustomerService
import net.elau.example.springcloudkafkaexample.web.request.CreateCustomerRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val mapper: CustomerMapper,
    private val service: CustomerService
) {

    @PostMapping
    fun create(@RequestBody createCustomerRequest: CreateCustomerRequest) =
        service.create(mapper.toDto(createCustomerRequest))
}
