package net.elau.example.springcloudkafkaexample.integration.stream.produce

import net.elau.example.springcloudkafkaexample.integration.stream.event.CustomerCreatedEvent
import org.springframework.context.annotation.Bean
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST
import java.util.function.Supplier

@Component
class CustomerCreatedProducer : Supplier<Flux<Message<CustomerCreatedEvent>>> {

    private val sink = Sinks.many().unicast().onBackpressureBuffer<Message<CustomerCreatedEvent>>()

    fun produce(customerCreatedEvent: CustomerCreatedEvent) {
        val message = MessageBuilder
            .withPayload(customerCreatedEvent)
            .setHeader(KafkaHeaders.KEY, customerCreatedEvent.id)
            .build()
        sink.emitNext(message, FAIL_FAST)
    }

    override fun get(): Flux<Message<CustomerCreatedEvent>> = sink.asFlux()
}