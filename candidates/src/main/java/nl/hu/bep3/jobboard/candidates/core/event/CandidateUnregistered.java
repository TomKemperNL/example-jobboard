package nl.hu.bep3.jobboard.candidates.core.event;

import java.util.UUID;

public class CandidateUnregistered extends CandidateEvent {
    private final UUID candidate;

    public CandidateUnregistered(UUID id) {
        this.candidate = id;
    }

    @Override
    public String getEventKey() {
        return "candidates.unregistered";
    }

    public UUID getCandidate() {
        return candidate;
    }
}
