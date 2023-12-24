package ba.edu.ibu.job.search.platform.api.impl.mailsender;

import ba.edu.ibu.job.search.platform.core.api.mailsender.MailSender;
import ba.edu.ibu.job.search.platform.core.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
// Method 2 for injection: condition based on application properties
// @ConditionalOnProperty(name = "configuration.mailsender.default", havingValue = "mailgun")
public class MailgunSender implements MailSender {

    private final RestTemplate restTemplate;
    private final String fromEmail;

    public MailgunSender(RestTemplate restTemplate, String fromEmail) {
        this.restTemplate = restTemplate;
        this.fromEmail = fromEmail;
    }

    @Override
    public String send(List<User> users, String message) {
//        for (User user: users) {
//            System.out.println("Message sent to: " + user.getEmail());
//        }
//        return "Message: " + message + " | Sent via Mailgun.";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("from", fromEmail);
        for (User user: users) {
            map.add("to", user.getEmail());
        }
        map.add("subject", "Test message");
        map.add("text", message);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        return restTemplate.postForEntity("/messages", request, String.class).getBody();
    }
}

