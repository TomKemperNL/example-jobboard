package nl.hu.bep3.jobboard.candidates.core.event;

import java.util.UUID;

public class CandidateAddedKeyword extends CandidateEvent {
    private final UUID candidate;
    private final String keyword;

    public CandidateAddedKeyword(UUID candidate, String keyword) {
        this.candidate = candidate;
        this.keyword = keyword;
    }

    @Override
    public String getEventKey() {
        return "keywords.candidate.added";
    }

    public UUID getCandidate() {
        return candidate;
    }

    public String getKeyword() {
        return keyword;
    }
}
