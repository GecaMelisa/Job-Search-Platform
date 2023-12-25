package ba.edu.ibu.job.search.platform.core.exceptions.auth;


import ba.edu.ibu.job.search.platform.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends GeneralException {
    public EntityNotFoundException() {
        super(HttpStatus.NOT_FOUND.value());
    }

    public EntityNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }
}
