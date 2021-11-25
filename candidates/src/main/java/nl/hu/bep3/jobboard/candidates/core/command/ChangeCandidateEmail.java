package nl.hu.bep3.jobboard.candidates.core.command;

import java.util.UUID;

public class ChangeCandidateEmail {
    private final UUID id;
    private final String email;

    public ChangeCandidateEmail(UUID id, String email) {
        this.id = id;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
