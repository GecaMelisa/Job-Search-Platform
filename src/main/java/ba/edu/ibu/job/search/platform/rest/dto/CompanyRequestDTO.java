package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.Company;

import java.util.Date;

public class CompanyRequestDTO {
    private String companyName;
    private String address;
    private int phone;
    private String email;


    /**Converting from a model to a DTO

    Empty constructor*/
    public CompanyRequestDTO() {
    }

    /**Constructor which takes in the Model object as parameter*/
    public CompanyRequestDTO(Company company) {
        this.companyName = company.getCompanyName();
        this.address = company.getAddress();
        this.phone = company.getPhone();
        this.email = company.getEmail();
    }

    /**Converting from a DTO to a model with method toEntity which creates an empty Model object and assigns the data from the DTO to the model*/
    public Company toEntity() {
        Company company = new Company();
        company.setCompanyName(companyName);
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
