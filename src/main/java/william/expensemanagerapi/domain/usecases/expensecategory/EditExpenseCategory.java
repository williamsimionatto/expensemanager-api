package william.expensemanagerapi.domain.usecases.expensecategory;

import william.expensemanagerapi.domain.entities.ExpenseCategory;
import william.expensemanagerapi.domain.model.EditExpenseCategoryModel;

public interface EditExpenseCategory {
  public ExpenseCategory edit(Long id, EditExpenseCategoryModel params);
}
