package william.expensemanagerapi.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
  public String name;

  @NotBlank(message = "Start date is required")
  @JsonFormat(pattern = "yyyy-MM-dd")
  public String startDate;

  @NotBlank(message = "End date is required")
  @JsonFormat(pattern = "yyyy-MM-dd")
  public String endDate;

  @NotBlank(message = "Budget is required")
  @Min(value = 1, message = "Budget must be greater than or equal to 1")
  public Double budget;
}
