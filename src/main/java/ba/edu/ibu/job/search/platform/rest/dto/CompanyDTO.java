package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.Company;


public class CompanyDTO {
        private String id;
        private String companyName;
        private String address;
        private int phone;
        private String email;

        public CompanyDTO(Company company){
            this.id = company.getId();
            this.companyName=company.getCompanyName();
            this.address=company.getAddress();
            this.phone=company.getPhone();
            this.email = company.getEmail();
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
