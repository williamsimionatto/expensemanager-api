package william.expensemanagerapi.domain.usecases;

import java.util.List;

import william.expensemanagerapi.domain.entities.ExpenseCategory;

public interface LoadExpenseCategories {
  public List<ExpenseCategory> load();
}
