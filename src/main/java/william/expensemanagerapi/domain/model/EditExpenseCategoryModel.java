package william.expensemanagerapi.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditExpenseCategoryModel {
  @NotBlank(message = "Name is required")
  public String name;
  @NotEmpty(message = "Description is required")
  public String description;
}
