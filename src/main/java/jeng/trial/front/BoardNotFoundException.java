package jeng.trial.front;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by abelsr on 11/06/2016.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BoardNotFoundException extends RuntimeException {

    public BoardNotFoundException() {
        super();
    }

    public BoardNotFoundException(String message) {
        super(message);
    }
}
