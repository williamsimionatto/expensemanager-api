package william.expensemanagerapi.domain.usecases.expense;

import william.expensemanagerapi.domain.entities.Expense;
import william.expensemanagerapi.domain.model.AddExpenseModel;

public interface AddExpense {
  public Expense add(AddExpenseModel params);
}
