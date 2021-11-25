package nl.hu.bep3.jobboard.candidates.core.command;

import java.util.UUID;

public class RenameCandidate {
    private final UUID id;
    private final String name;

    public RenameCandidate(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
