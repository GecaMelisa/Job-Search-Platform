package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;
import ba.edu.ibu.job.search.platform.core.model.User;

import java.util.List;

public class CompanyOwnerRequestDTO extends UserRequestDTO {


    //Empty constructos
    public CompanyOwnerRequestDTO() {
    }

    public CompanyOwnerRequestDTO(User companyOwner) {
        super(companyOwner);
    }

    public CompanyOwner toEntity() {
        CompanyOwner companyOwner = new CompanyOwner();

        companyOwner.setFirstName(this.getFirstName());
        companyOwner.setLastName(this.getLastName());
        companyOwner.setEmail(this.getEmail());
        companyOwner.setAddress(this.getAddress());
        companyOwner.setPhoneNumber(this.getPhoneNumber());
        companyOwner.setPassword(this.getPassword());
        companyOwner.setDateOfBirth(this.getDateOfBirth());
        companyOwner.setUsername(this.getUsername());

        return companyOwner;
    }
}
