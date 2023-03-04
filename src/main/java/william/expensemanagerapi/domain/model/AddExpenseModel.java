package william.expensemanagerapi.domain.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddExpenseModel {
  @NotNull(message = "Date is required")
  @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
  private Date date;

  @NotNull(message = "Amount is required")
  @Min(value = 1, message = "Amount must be greater than or equal to 1")
  private Double amount;

  @NotNull(message = "Category is required")
  private Long categoryId;

  @NotNull(message = "Period is required")
  private Long periodId;
  
  @NotEmpty(message = "Description is required")
  public String description;  
}
