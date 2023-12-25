package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import ba.edu.ibu.job.search.platform.core.repository.CompanyOwnerRepository;
import ba.edu.ibu.job.search.platform.core.repository.UserRepository;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyOwnerDTO;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyOwnerRequestDTO;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

@Service
public class CompanyOwnerService {
    private CompanyOwnerRepository companyOwnerRepository;
    private UserRepository userRepository;

    public CompanyOwnerService(CompanyOwnerRepository companyOwnerRepository, UserRepository userRepository){
        this.companyOwnerRepository=companyOwnerRepository;
        this.userRepository=userRepository;
    }

    public List<CompanyOwnerDTO> getCompanyOwners(){
        List<CompanyOwner> companyOwners= companyOwnerRepository.findAll();
        return companyOwners
                .stream()
                .map(CompanyOwnerDTO::new)
                .collect(toList());
    }

    public CompanyOwnerDTO getCompanyOwnerById(String id){
        Optional<CompanyOwner> companyOwner = companyOwnerRepository.findById(id);
        if(companyOwner.isEmpty()){
            throw new ResourceNotFoundException("The company owner with the given ID does not exist.");
        }
        return new CompanyOwnerDTO(companyOwner.get());
    }

    /**need this for assigning companyOwner to company
    public CompanyOwner getCompanyOwnerById2(String id){
        Optional<CompanyOwner> companyOwner = companyOwnerRepository.findById(id);
        if(companyOwner.isEmpty()){
            throw new ResourceNotFoundException("The Company owner with the given ID does not exist.");
        }
        return companyOwner.get();
    }
     */


    public CompanyOwnerDTO addCompanyOwner(CompanyOwnerRequestDTO payload) {

        CompanyOwner companyOwner = payload.toEntity();
        companyOwner.setUserType(UserType.COMPANY_OWNER);
        companyOwnerRepository.save(companyOwner);

        userRepository.save(companyOwner);
        return new CompanyOwnerDTO(companyOwner);
    }

    public  CompanyOwnerDTO updateCompanyOwner(String id, CompanyOwnerRequestDTO payload){
        Optional<CompanyOwner> companyOwner = companyOwnerRepository.findById(id);
        if(companyOwner.isEmpty()){
            throw new ResourceNotFoundException("The trainer with the given ID does not exist.");
        }
        CompanyOwner updatedCompanyOwner= payload.toEntity();
        updatedCompanyOwner.setId(companyOwner.get().getId());
        updatedCompanyOwner=companyOwnerRepository.save(updatedCompanyOwner);
        userRepository.save(updatedCompanyOwner);
        return new CompanyOwnerDTO(updatedCompanyOwner);
    }

    public void deleteCompanyOwner(String id){

        Optional<CompanyOwner> companyOwner = companyOwnerRepository.findById(id);
        companyOwner.ifPresent(companyOwner1 -> {
            companyOwnerRepository.delete(companyOwner1);
            userRepository.delete(companyOwner1);
        });

    }
}
