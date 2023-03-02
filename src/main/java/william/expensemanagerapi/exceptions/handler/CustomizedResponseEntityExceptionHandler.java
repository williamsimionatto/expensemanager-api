package william.expensemanagerapi.exceptions.handler;

import java.util.Date;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import william.expensemanagerapi.exceptions.ExceptionReponse;

@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionReponse> handleAllException(
    Exception ex,
    WebRequest request
  ) {
    ExceptionReponse exceptionResponse = new ExceptionReponse(
      new Date(),
      ex.getMessage(),
      ex.getLocalizedMessage()
    );

    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ObjectNotFoundException.class)
  public final ResponseEntity<ExceptionReponse> handleNotFoundException(
    ObjectNotFoundException ex,
    WebRequest request
  ) {
    ExceptionReponse exceptionResponse = new ExceptionReponse(
      new Date(),
      ex.getMessage(),
      ex.getLocalizedMessage()
    );

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
}
