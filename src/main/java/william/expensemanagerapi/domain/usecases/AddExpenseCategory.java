package william.expensemanagerapi.domain.usecases;

import william.expensemanagerapi.domain.model.AddExpenseCategoryModel;

public interface AddExpenseCategory {
  public void add(AddExpenseCategoryModel params);
}
