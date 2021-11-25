package nl.hu.bep3.jobboard.candidates.core;

import nl.hu.bep3.jobboard.candidates.core.query.FindCandidatesByKeyword;
import nl.hu.bep3.jobboard.candidates.core.query.GetCandidateById;
import nl.hu.bep3.jobboard.candidates.core.query.ListCandidates;
import nl.hu.bep3.jobboard.candidates.core.exception.CandidateNotFound;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatesQueryHandler {
    private final CandidateRepository repository;

    public CandidatesQueryHandler(CandidateRepository repository) {
        this.repository = repository;
    }

    public Candidate handle(GetCandidateById query) {
        return this.repository.findById(query.getId())
                .orElseThrow(() -> new CandidateNotFound(query.getId().toString()));
    }

    public List<Candidate> handle(FindCandidatesByKeyword query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findByKeywordsEquals(query.getKeyword(), sort);
    }

    public List<Candidate> handle(ListCandidates query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findAll(sort);
    }

    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

        if (direction.equals("desc")) {
            sort = sort.descending();
        }

        return sort;
    }
}
