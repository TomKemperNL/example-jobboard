package nl.hu.bep3.jobboard.candidates.web.request;

import javax.validation.constraints.NotBlank;

public class KeywordRequest {
    @NotBlank
    public String keyword;
}
