package william.expensemanagerapi.exceptions;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import william.expensemanagerapi.exceptions.error.FieldError;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvalidParamsResponse implements Serializable {
  private Date timestamp;
  private String message;
  private List<FieldError> errors;
}
