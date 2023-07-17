package net.elau.example.springcloudkafkaexample.integration.stream.event

data class CustomerCreatedEvent(

    val id: String,

    val firstName: String,

    val lastName: String,

    val email: String
)
