package nl.hu.bep3.jobboard.candidates.core;

import nl.hu.bep3.jobboard.candidates.core.command.*;
import nl.hu.bep3.jobboard.candidates.core.event.CandidateEvent;
import nl.hu.bep3.jobboard.candidates.core.exception.CandidateNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CandidatesCommandHandler {
    private final CandidateRepository repository;
    private final CandidateEventPublisher eventPublisher;
    private final JobRepository jobGateway;

    public CandidatesCommandHandler(CandidateRepository repository, CandidateEventPublisher eventPublisher, JobRepository jobGateway) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.jobGateway = jobGateway;
    }

    public Candidate handle(RegisterCandidate command) {
        Candidate candidate = new Candidate(command.getName(), command.getEmail());

        this.publishEventsFor(candidate);
        this.repository.save(candidate);

        return candidate;
    }

    public Candidate handle(RenameCandidate command) {
        Candidate candidate = this.getCandidateById(command.getId());

        candidate.rename(command.getName());
        this.publishEventsFor(candidate);
        this.repository.save(candidate);

        return candidate;
    }

    public Candidate handle(ChangeCandidateEmail command) {
        Candidate candidate = this.getCandidateById(command.getId());

        candidate.changeEmail(command.getEmail());
        this.publishEventsFor(candidate);
        this.repository.save(candidate);

        return candidate;
    }

    public Candidate handle(AddKeyword command) {
        Candidate candidate = this.getCandidateById(command.getId());

        candidate.addKeyword(command.getKeyword());
        this.jobGateway.findJobsByKeyword(command.getKeyword())
                .forEach(candidate::addJob);

        this.publishEventsFor(candidate);
        this.repository.save(candidate);

        return candidate;
    }

    public Candidate handle(RemoveKeyword command) {
        Candidate candidate = this.getCandidateById(command.getId());

        candidate.removeKeyword(command.getKeyword());
        this.publishEventsFor(candidate);
        this.repository.save(candidate);

        return candidate;
    }

    public void handle(MatchCandidates command) {
        // There's probably a faster way to do this using
        // a custom query in the repository (or by using a relational DB)
        this.repository.findByKeywordsEquals(command.getKeyword())
                .forEach(candidate -> {
                    candidate.addJob(command.getJob());
                    this.repository.save(candidate);
                });
    }

    public void handle(UnmatchCandidates command) {
        // There's probably a faster way to do this using
        // a custom query in the repository (or by using a relational DB)
        this.repository.findByKeywordsEquals(command.getKeyword())
                .forEach(candidate -> {
                    candidate.removeJob(command.getJob());
                    this.repository.save(candidate);
                });
    }

    private Candidate getCandidateById(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new CandidateNotFound(id.toString()));
    }

    private void publishEventsFor(Candidate candidate) {
        List<CandidateEvent> events = candidate.listEvents();
        events.forEach(eventPublisher::publish);
        candidate.clearEvents();
    }
}
