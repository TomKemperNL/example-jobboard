package nl.hu.bep3.jobboard.candidates.messaging.event;

import java.time.Instant;
import java.util.UUID;

public class JobKeywordEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;
    public UUID job;
    public String keyword;
}
