package nl.hu.bep3.jobboard.candidates.core.event;

import java.util.UUID;

public class CandidateRemovedKeyword extends CandidateEvent {
    private final UUID candidate;
    private final String keyword;

    public CandidateRemovedKeyword(UUID candidate, String keyword) {
        this.candidate = candidate;
        this.keyword = keyword;
    }

    @Override
    public String getEventKey() {
        return "keywords.candidate.removed";
    }

    public UUID getCandidate() {
        return candidate;
    }

    public String getKeyword() {
        return keyword;
    }
}
