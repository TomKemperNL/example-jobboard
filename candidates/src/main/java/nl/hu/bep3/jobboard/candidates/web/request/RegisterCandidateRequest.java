package nl.hu.bep3.jobboard.candidates.web.request;

import javax.validation.constraints.NotBlank;

public class RegisterCandidateRequest {
    @NotBlank
    public String name;

    @NotBlank
    public String email;
}
