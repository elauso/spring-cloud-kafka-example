package net.elau.example.springcloudkafkaexample.service

import net.elau.example.springcloudkafkaexample.dto.CreateCustomerDto
import net.elau.example.springcloudkafkaexample.integration.stream.produce.CustomerCreatedProducer
import net.elau.example.springcloudkafkaexample.mapper.CustomerMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val mapper: CustomerMapper,
    private val customerCreatedProducer: CustomerCreatedProducer
) {
    companion object {
        private val log = LoggerFactory.getLogger(CustomerService::class.java)
    }

    fun create(createCustomerDto: CreateCustomerDto) {
        log.debug("m=create, msg=Create customer={}", createCustomerDto)
        customerCreatedProducer.produce(mapper.toEvent(createCustomerDto))
    }
}