package william.expensemanagerapi.domain.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddPeriodModel {
  @NotBlank(message = "Name is required")
  private String name;

  @NotNull(message = "Start date is required")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date startDate;

  @NotNull(message = "End date is required")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date endDate;

  @Min(value = 1, message = "Budget must be greater than or equal to 1")
  @NotNull(message = "Budget is required")
  private Double budget;
}
