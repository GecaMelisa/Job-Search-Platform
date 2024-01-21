package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;

import java.util.List;

public class CompanyOwnerRequestDTO extends UserRequestDTO {


    //Empty constructos
    public CompanyOwnerRequestDTO() {
    }

    public CompanyOwnerRequestDTO(CompanyOwner companyOwner) {

    }

    public CompanyOwner toEntity() {
        CompanyOwner companyOwner = new CompanyOwner();

        companyOwner.setFirstName(this.getFirstName());
        companyOwner.setLastName(this.getLastName());
        companyOwner.setEmail(this.getEmail());
        companyOwner.setAddress(this.getAddress());
        companyOwner.setPhoneNumber(this.getPhoneNumber());

        return companyOwner;
    }
}
