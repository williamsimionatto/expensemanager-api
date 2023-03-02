package william.expensemanagerapi.domain.entities;

import java.io.Serializable;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import william.expensemanagerapi.domain.model.AddExpenseCategoryModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "expense_category")
public class ExpenseCategory implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NonNull
  @Column(name = "name")
  private String name;

  @NonNull
  @Column(name = "description")
  private String description;

  public ExpenseCategory(AddExpenseCategoryModel params) {
    this.name = params.getName();
    this.description = params.getDescription();
  }
}

