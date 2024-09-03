package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.enums.ApplicationResponse;

public class UpdateApplicationResponseDTO {

    private ApplicationResponse response;
    private String toEmail;

    public UpdateApplicationResponseDTO() {}

    public UpdateApplicationResponseDTO(ApplicationResponse response, String toEmail) {
        this.response = response;
        this.toEmail = toEmail;
    }

    public ApplicationResponse getResponse() {
        return response;
    }

    public void setResponse(ApplicationResponse response) {
        this.response = response;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }
}
