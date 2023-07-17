package net.elau.example.springcloudkafkaexample.web.request

data class CreateCustomerRequest(

    val firstName: String,

    val lastName: String,

    val email: String
)
