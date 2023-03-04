package william.expensemanagerapi.domain.model;

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
public class AddPeriodCategoryModel {
  @NotBlank(message = "Category is required")
  private Long categoryId;

  @NotBlank(message = "Budget is required")
  @Min(value = 1, message = "Budget must be greater than or equal to 1")
  private Double budget;
}
