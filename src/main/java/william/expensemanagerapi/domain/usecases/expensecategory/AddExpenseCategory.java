package william.expensemanagerapi.domain.usecases.expensecategory;

import william.expensemanagerapi.domain.entities.ExpenseCategory;
import william.expensemanagerapi.domain.model.AddExpenseCategoryModel;

public interface AddExpenseCategory {
  public ExpenseCategory add(AddExpenseCategoryModel params);
}
