package ba.edu.ibu.job.search.platform.api.impl.mailsender;

import ba.edu.ibu.job.search.platform.core.api.mailsender.MailSender;
import ba.edu.ibu.job.search.platform.core.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
// Method 2 for injection: condition based on application properties
// @ConditionalOnProperty(name = "configuration.mailsender.default", havingValue = "sendgrid")
public class AWSSender implements MailSender {

    @Override
    public String send(List<User> users, String message) {
        for (User user: users) {
            System.out.println("Message sent to: " + user.getEmail());
        }
        return "Message: " + message + " | Sent via AWS.";
    }
}