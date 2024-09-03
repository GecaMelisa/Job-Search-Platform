package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;
import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import org.springframework.data.annotation.Id;

import java.util.List;


public class CompanyDTO {

        private String id;
        private String companyName;
        private CompanyOwner companyOwner;
        private String companyOwnerId;
        private String address;
        private String phone;
        private String email;
        private String description;

    public CompanyDTO(Company company){
            this.id=company.getId();
            this.companyName=company.getCompanyName();
            this.address=company.getAddress();
            this.phone=company.getPhone();
            this.email = company.getEmail();
            this.companyOwner=company.getCompanyOwner();
            this.description=company.getDescription();
        }

        public String getId() {
        return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyOwnerId() {
            return companyOwnerId;
        }

        public void setCompanyOwnerId(String companyOwnerId) {
            this.companyOwnerId = companyOwnerId;
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

    public CompanyOwner getCompanyOwner() {
        return companyOwner;
    }

    public void setCompanyOwner(CompanyOwner companyOwner) {
        this.companyOwner = companyOwner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
