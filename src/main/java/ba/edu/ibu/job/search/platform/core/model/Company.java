package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class Company {
    @Id
    private String id;
    private  CompanyOwner companyOwner;
    private String companyOwnerId;
    private String companyName;
    private String address;
    private String phone;
    private String email;
    private List<Job> jobs;
    private List<User> Employees;
    private boolean approvedByAdmin;
    private StatusRequest statusRequest;

    public Company(){};

    public Company(String id, CompanyOwner companyOwner, String companyName, String address,
                   String phone, String email, List<Job> jobs,
                   boolean approvedByAdmin, StatusRequest statusRequest) {
        this.id = id;
        this.companyOwner = companyOwner;
        this.companyOwnerId=companyOwner.getId();
        this.companyName = companyName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.jobs = jobs;
        this.approvedByAdmin = approvedByAdmin;
        this.statusRequest = statusRequest;
    }



    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CompanyOwner getCompanyOwner() {
        return companyOwner;
    }

    public void setCompanyOwner(CompanyOwner companyOwner) {
        this.companyOwner = companyOwner;
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

    public List<User> getEmployees() {
        return Employees;
    }

    public void setEmployees(List<User> employees) {
        Employees = employees;
    }

    public boolean isApprovedByAdmin() {
        return approvedByAdmin;
    }

    public void setApprovedByAdmin(boolean approvedByAdmin) {
        this.approvedByAdmin = approvedByAdmin;
    }

    public StatusRequest getStatusRequest() {
        return statusRequest;
    }

    public void setStatusRequest(StatusRequest statusRequest) {
        this.statusRequest = statusRequest;
    }

    public String getCompanyOwnerId() {
        return companyOwnerId;
    }

    public void setCompanyOwnerId(String companyOwnerId) {
        this.companyOwnerId = companyOwnerId;
    }
}