package net.elau.example.springcloudkafkaexample.mapper

import net.elau.example.springcloudkafkaexample.dto.CreateCustomerDto
import net.elau.example.springcloudkafkaexample.integration.stream.event.CustomerCreatedEvent
import net.elau.example.springcloudkafkaexample.web.request.CreateCustomerRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface CustomerMapper {

    fun toDto(createCustomerRequest: CreateCustomerRequest): CreateCustomerDto

    @Mappings(
        Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    )
    fun toEvent(createCustomerDto: CreateCustomerDto): CustomerCreatedEvent
}