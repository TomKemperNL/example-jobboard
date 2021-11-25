package nl.hu.bep3.jobboard.candidates.core.query;

import java.util.UUID;

public class GetCandidateById {
    private final UUID id;

    public GetCandidateById(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
