package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;

import java.util.List;


public class CompanyDTO {

        private String id;
        private String companyName;
        private String companyOwnerId;
        private String address;
        private String phone;
        private String email;
        private StatusRequest statusRequest;

        public CompanyDTO(Company company){
            this.id=company.getId();
            this.companyName=company.getCompanyName();
            this.address=company.getAddress();
            this.phone=company.getPhone();
            this.email = company.getEmail();
            this.statusRequest=company.getStatusRequest();
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

        public StatusRequest getStatusRequest() {
            return statusRequest;
        }
        public void setStatusRequest(StatusRequest statusRequest) {
            this.statusRequest = statusRequest;
        }


}
