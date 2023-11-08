package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;

import java.util.Date;

public class CompanyRequestDTO {
    private String companyName;
    private String companyOwnerId;
    private String address;
    private String phone;
    private String email;


    /**Converting from a model to a DTO

    Empty constructor*/
    public CompanyRequestDTO() {
    }

    /**Constructor which takes in the Model object as parameter*/
    public CompanyRequestDTO(Company company, String companyOwnerId) {
        this.companyName = company.getCompanyName();
        this.companyOwnerId=companyOwnerId;
        this.address = company.getAddress();
        this.phone = company.getPhone();
        this.email = company.getEmail();

    }

    /**Converting from a DTO to a model with method toEntity which creates an empty Model object and assigns the data from the DTO to the model*/
    public Company toEntity() {
        Company company = new Company();
        company.setCompanyName(companyName);
        this.setCompanyOwnerId(this.companyOwnerId);
        company.setAddress(address);
        company.setPhone(phone);
        company.setEmail(email);
        return company;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyOwnerId() {
        return companyOwnerId;
    }

    public void setCompanyOwnerId(String companyOwnerId) {
        this.companyOwnerId = companyOwnerId;
    }
}
