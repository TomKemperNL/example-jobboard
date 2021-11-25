package nl.hu.bep3.jobboard.candidates.core.exception;

public class CandidateNotFound extends RuntimeException {
    public CandidateNotFound(String message) {
        super(message);
    }
}
