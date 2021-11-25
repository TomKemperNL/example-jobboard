package nl.hu.bep3.jobboard.candidates.core.command;

public class RegisterCandidate {
    private final String name;
    private final String email;

    public RegisterCandidate(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
