package william.expensemanagerapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddExpenseCategoryModel {
  public Long id;
  public String name;
  public String description;
}
