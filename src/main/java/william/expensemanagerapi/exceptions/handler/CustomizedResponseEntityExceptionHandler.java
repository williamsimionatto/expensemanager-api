package william.expensemanagerapi.exceptions.handler;

import java.util.Date;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import william.expensemanagerapi.exceptions.ExceptionReponse;
import william.expensemanagerapi.exceptions.InvalidParamsResponse;
import william.expensemanagerapi.exceptions.error.FieldError;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {
  @ExceptionHandler(IllegalArgumentException.class)
  public final ResponseEntity<ExceptionReponse> handleIllegalArgumentException(
    IllegalArgumentException ex,
    WebRequest request
  ) {
    ExceptionReponse exceptionResponse = new ExceptionReponse(
      new Date(),
      ex.getMessage(),
      ex.getLocalizedMessage()
    );

    return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
  }

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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex,
    WebRequest request
  ) {
    List<FieldError> fieldError = ex.getBindingResult().getFieldErrors()
      .stream()
      .map(error -> new FieldError(error.getField(), error.getDefaultMessage()))
      .toList();

      InvalidParamsResponse exceptionResponse = new InvalidParamsResponse(
      new Date(),
      "Invalid data",
      fieldError
    );

    return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
