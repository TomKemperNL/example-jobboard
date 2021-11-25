package nl.hu.bep3.jobboard.candidates.web;

import nl.hu.bep3.jobboard.candidates.core.CandidatesCommandHandler;
import nl.hu.bep3.jobboard.candidates.core.CandidatesQueryHandler;
import nl.hu.bep3.jobboard.candidates.core.command.*;
import nl.hu.bep3.jobboard.candidates.core.query.FindCandidatesByKeyword;
import nl.hu.bep3.jobboard.candidates.core.query.GetCandidateById;
import nl.hu.bep3.jobboard.candidates.core.query.ListCandidates;
import nl.hu.bep3.jobboard.candidates.core.Candidate;
import nl.hu.bep3.jobboard.candidates.core.exception.CandidateNotFound;
import nl.hu.bep3.jobboard.candidates.web.request.ChangeCandidateEmailRequest;
import nl.hu.bep3.jobboard.candidates.web.request.KeywordRequest;
import nl.hu.bep3.jobboard.candidates.web.request.RegisterCandidateRequest;
import nl.hu.bep3.jobboard.candidates.web.request.RenameCandidateRequest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/*
    Note that we are combining ideas of REST and CQRS
    in a task-based API.
    We are still resource-centric, but allow verb-like
    commands to be added as sub-resources using POST.

    See: https://codeopinion.com/is-a-rest-api-with-cqrs-possible/
 */
@RestController
@RequestMapping("/candidates")
public class CandidatesController {
    private final CandidatesCommandHandler commandHandler;
    private final CandidatesQueryHandler queryHandler;

    public CandidatesController(CandidatesQueryHandler queryHandler, CandidatesCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping
    public Candidate registerCandidate(@Valid @RequestBody RegisterCandidateRequest request) {
        return this.commandHandler.handle(
                new RegisterCandidate(request.name, request.email)
        );
    }

    @PostMapping("/{id}/rename")
    public Candidate renameCandidate(@PathVariable UUID id, @Valid @RequestBody RenameCandidateRequest request) {
        return this.commandHandler.handle(new RenameCandidate(id, request.name));
    }

    @PostMapping("/{id}/change-email")
    public Candidate changeEmail(@PathVariable UUID id, @Valid @RequestBody ChangeCandidateEmailRequest request) {
        return this.commandHandler.handle(new ChangeCandidateEmail(id, request.email));
    }

    @PostMapping("/{id}/keyword")
    public Candidate addKeyword(@PathVariable UUID id, @Valid @RequestBody KeywordRequest request) {
        return this.commandHandler.handle(new AddKeyword(id, request.keyword));
    }

    @DeleteMapping("/{id}/keyword")
    public Candidate removeKeyword(@PathVariable UUID id, @Valid @RequestBody KeywordRequest request) {
        return this.commandHandler.handle(new RemoveKeyword(id, request.keyword));
    }

    @GetMapping("/{id}")
    public Candidate findCandidateById(@PathVariable UUID id) {
        return this.queryHandler.handle(new GetCandidateById(id));
    }

    @GetMapping(params = {"keyword"})
    public List<Candidate> findCandidatesByKeyword(
            @RequestParam String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return this.queryHandler.handle(
                new FindCandidatesByKeyword(keyword, orderBy, direction)
        );
    }

    @GetMapping()
    public List<Candidate> findCandidates(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return this.queryHandler.handle(new ListCandidates(orderBy, direction));
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleCandidateNotFound(CandidateNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleDuplicate(DuplicateKeyException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
