package nl.hu.bep3.jobboard.candidates.core.command;

import java.util.UUID;

public class MatchCandidates {
    private final UUID job;
    private final String keyword;

    public MatchCandidates(UUID job, String keyword) {
        this.job = job;
        this.keyword = keyword;
    }

    public UUID getJob() {
        return job;
    }

    public String getKeyword() {
        return keyword;
    }
}
