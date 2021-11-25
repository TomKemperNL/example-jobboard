package nl.hu.bep3.jobboard.candidates.messaging;

import nl.hu.bep3.jobboard.candidates.core.event.CandidateEvent;
import nl.hu.bep3.jobboard.candidates.core.CandidateEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements CandidateEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String jobBoardExchange;

    public RabbitMqEventPublisher(
            RabbitTemplate rabbitTemplate,
            String jobBoardExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.jobBoardExchange = jobBoardExchange;
    }

    public void publish(CandidateEvent event) {
        this.rabbitTemplate.convertAndSend(jobBoardExchange, event.getEventKey(), event);
    }
}
