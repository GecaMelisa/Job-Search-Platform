package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.api.mailsender.MailSender;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

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
     */
    // @Autowired
    // private MailSender mailSender;


    //Dependency Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String sendEmailToAllUsers(String message){
        List<User> users = userRepository.findAll();

        // Method 1: Using a specific implementation name
        return mailgunSender.send(users, message);
        // return sendgridSender.send(users, message);

        // Method 2: The appropriate implementation is decided based on configuration
        // return mailSender.send(users, message);
    }
}
