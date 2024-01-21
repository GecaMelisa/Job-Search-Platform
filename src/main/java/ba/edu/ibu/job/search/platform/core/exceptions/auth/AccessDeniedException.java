package ba.edu.ibu.job.search.platform.core.exceptions.auth;

import ba.edu.ibu.job.search.platform.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class AccessDeniedException extends GeneralException {
    public AccessDeniedException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    public AccessDeniedException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }
}