package ba.edu.ibu.job.search.platform.core.api.mailsender;

import ba.edu.ibu.job.search.platform.core.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MailSender {

    public String send(List<User> users, String message);
}