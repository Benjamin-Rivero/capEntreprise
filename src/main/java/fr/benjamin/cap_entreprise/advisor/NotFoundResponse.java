package fr.benjamin.cap_entreprise.advisor;



import fr.benjamin.cap_entreprise.custom_response.ResponseException;
import fr.benjamin.cap_entreprise.exception.NotFoundEntityException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundResponse {

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // Modifie le code HTTP de la réponse
    @ExceptionHandler(EntityNotFoundException.class) // L'exception qui doit être "catch"
    ResponseException notFoundResponseHandler(EntityNotFoundException e) {
        return new ResponseException();
    }

}
