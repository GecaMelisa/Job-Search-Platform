package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.api.mailsender.MailSender;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.job.search.platform.core.repository.UserRepository;
import ba.edu.ibu.job.search.platform.rest.dto.UserDTO;
import ba.edu.ibu.job.search.platform.rest.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
import java.io.NotActiveException;

@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Method 1: Using @Autowired with implementation names
     */

    @Autowired
    private MailSender mailgunSender;

    @Autowired MailSender sendgridSender;

    /**
     * Method 2: Using @ConditionalOnProperty and application.yml

    @Autowired
    private MailSender mailSender;
    */

    /**Dependency Injection
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**Get all users - using UserDTO object rather than the full User object
     * only admin */

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();

        return users
                .stream()
                .map(UserDTO::new)
                .collect(toList());
    }

    /**Get a user by ID - only admin */
    public UserDTO getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        return new UserDTO(user.get());
    }
    /**Get a user by ID*/
    public User getUserById2(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        return user.get();
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmailCustom(email); {
            if(user.isEmpty()){
                throw new ResourceNotFoundException("The user with the given ID does not exist.");
            }
            return user.get();

            }
        }


    /**Add a user - converting it to a User instance by using the toEntity() method
    If we provide a model without an ID, it will create it */
    public UserDTO addUser(UserRequestDTO payload) {
        User user = userRepository.save(payload.toEntity());
        return new UserDTO(user);
    }

    /**Update a user - if we provide an ID to a model, the save method will update model*/
    public UserDTO updateUser(String id, UserRequestDTO payload) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        User updatedUser = payload.toEntity();
        updatedUser.setId(user.get().getId());
        updatedUser = userRepository.save(updatedUser);
        return new UserDTO(updatedUser);
    }

    /**Delete a user by ID*/
    public void deleteUser(String id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }

    /**Filter users by email
    @RequestMapping(method = RequestMethod.GET, path = "/filter")
    public ResponseEntity<UserDTO> filterUser(@RequestParam String email){
        return ResponseEntity.ok(userService.filterByEmail(email));
    }
    */



    public UserDTO filterByEmail(String email) {
        Optional<User> user = userRepository.findFirstByEmailLike(email);
        // Optional<User> user = userRepository.findByEmailCustom(email);
        return user.map(UserDTO::new).orElse(null);
    }


    public String sendEmailToAllUsers(String message){
        List<User> users = userRepository.findAll();

        // Method 1: Using a specific implementation name
        return mailgunSender.send(users, message);
        // return sendgridSender.send(users, message);

        // Method 2: The appropriate implementation is decided based on configuration
        // return mailSender.send(users, message);
    }
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByUsernameOrEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }


}
