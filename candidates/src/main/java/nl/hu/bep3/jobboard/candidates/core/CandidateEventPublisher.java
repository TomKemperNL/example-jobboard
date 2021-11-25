package nl.hu.bep3.jobboard.candidates.core;

import nl.hu.bep3.jobboard.candidates.core.event.CandidateEvent;

public interface CandidateEventPublisher {
    void publish(CandidateEvent event);
}
