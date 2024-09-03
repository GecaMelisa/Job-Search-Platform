package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import ba.edu.ibu.job.search.platform.core.repository.UserRepository;
import ba.edu.ibu.job.search.platform.rest.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
    private final UserRepository userRepository;
    private CompanyOwnerService companyOwnerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, CompanyOwnerService companyOwnerService) {
        this.userRepository = userRepository;
        this.companyOwnerService = companyOwnerService;
    }

    public UserDTO signUp(UserRequestDTO userRequestDTO) {
        userRequestDTO.setPassword(
                passwordEncoder.encode(userRequestDTO.getPassword())
        );
        User user = new User();
        if (userRequestDTO.getUserType() == UserType.COMPANY_OWNER) {
            return this.companyOwnerService.addCompanyOwner(new CompanyOwnerRequestDTO(userRequestDTO.toEntity()));
        }
        else {
            user = userRepository.save(userRequestDTO.toEntity());
        }

        return new UserDTO(user);
    }

    public LoginDTO signIn(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
        );
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("This user does not exist."));
        String jwt = jwtService.generateToken(user);

        return new LoginDTO(jwt);
    }

}

