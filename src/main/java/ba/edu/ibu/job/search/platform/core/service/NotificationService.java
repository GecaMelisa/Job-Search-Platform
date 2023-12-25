package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.rest.websockets.MainSocketHandler;
import org.springframework.stereotype.Service;
import java.io.IOException;
@Service
public class NotificationService {

    //Dependency-Injection
    private final MainSocketHandler mainSocketHandler;

    public NotificationService(MainSocketHandler mainSocketHandler) {
        this.mainSocketHandler = mainSocketHandler;
    }

    public void broadcastMessage(String message) throws IOException {
        mainSocketHandler.broadcastMessage(message);
    }

    public void sendMessage(String userId, String message) {
        mainSocketHandler.sendMessage(userId, message);
    }
}