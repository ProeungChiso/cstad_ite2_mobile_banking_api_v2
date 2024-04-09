package co.istad.ite2_mbanking_api_v2.exception;

import co.istad.ite2_mbanking_api_v2.base.BasedError;
import co.istad.ite2_mbanking_api_v2.base.BasedErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestControllerAdvice
public class ServiceException {
    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handleServiceErrors(ResponseStatusException ex) {
        BasedError<String> basedError = new BasedError<>();

        basedError.setCode(ex.getStatusCode().toString());
        basedError.setDescription(ex.getReason());

        BasedErrorResponse basedErrorResponse = new BasedErrorResponse();
        basedErrorResponse.setError(basedError);

        return ResponseEntity.status(ex.getStatusCode())
                .body(basedErrorResponse);
    }
}
