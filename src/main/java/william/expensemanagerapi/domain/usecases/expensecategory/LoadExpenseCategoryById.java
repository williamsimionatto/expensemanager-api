package william.expensemanagerapi.domain.usecases.expensecategory;

import william.expensemanagerapi.domain.entities.ExpenseCategory;

public interface LoadExpenseCategoryById {
  public ExpenseCategory get(Long id);
}
