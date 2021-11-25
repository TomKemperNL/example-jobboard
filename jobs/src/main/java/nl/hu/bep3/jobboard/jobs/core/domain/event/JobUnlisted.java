package nl.hu.bep3.jobboard.jobs.core.domain.event;

import java.util.UUID;

public class JobUnlisted extends JobEvent {
    private final UUID job;

    public JobUnlisted(UUID job) {
        this.job = job;
    }

    @Override
    public String getEventKey() {
        return "jobs.unlisted";
    }

    public UUID getJob() {
        return job;
    }
}
