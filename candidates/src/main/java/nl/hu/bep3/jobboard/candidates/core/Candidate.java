package nl.hu.bep3.jobboard.candidates.core;

import nl.hu.bep3.jobboard.candidates.core.event.CandidateAddedKeyword;
import nl.hu.bep3.jobboard.candidates.core.event.CandidateEvent;
import nl.hu.bep3.jobboard.candidates.core.event.CandidateRemovedKeyword;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/*
    Docs:
    https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongodb.repositories.queries
 */
@Document
public class Candidate {
    @Id
    private UUID id;
    @Indexed(unique = true)
    private String name;
    private String email;
    @Indexed
    private Set<String> keywords;
    @Indexed
    private Set<String> jobs;
    @Transient
    private List<CandidateEvent> events = new ArrayList<>();

    public Candidate(String name, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.keywords = new HashSet<>();
        this.jobs = new HashSet<>();
    }

    // Methods...
    public void rename(String name) {
        this.name = name;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
        this.events.add(new CandidateAddedKeyword(id, keyword));
    }

    public void removeKeyword(String keyword) {
        this.keywords.remove(keyword);
        this.events.add(new CandidateRemovedKeyword(id, keyword));
    }

    public void addJob(UUID job) {
        this.jobs.add(job.toString());
    }

    public void removeJob(UUID job) {
        this.jobs.remove(job.toString());
    }

    public Set<String> getJobs() {
        return jobs;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void clearEvents() {
        this.events.clear();
    }

    public List<CandidateEvent> listEvents() {
        return events;
    }
}
