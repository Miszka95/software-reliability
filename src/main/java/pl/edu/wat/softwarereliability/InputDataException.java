package pl.edu.wat.softwarereliability;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
class InputDataException extends RuntimeException {

    InputDataException(String message) {
        super(message);
    }
}
