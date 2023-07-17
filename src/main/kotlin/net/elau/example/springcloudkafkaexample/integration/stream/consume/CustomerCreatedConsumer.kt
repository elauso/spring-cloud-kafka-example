package net.elau.example.springcloudkafkaexample.integration.stream.consume

import net.elau.example.springcloudkafkaexample.integration.stream.event.CustomerCreatedEvent
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class CustomerCreatedConsumer {
    companion object {
        private val log = LoggerFactory.getLogger(CustomerCreatedConsumer::class.java)
    }

    @Bean
    fun customerCreatedConsume() = Consumer<CustomerCreatedEvent> { customerCreatedEvent ->
        log.debug("m=accept, msg=Consumed event={}", customerCreatedEvent)
    }
}
