package william.expensemanagerapi.domain.usecases.expensecategory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import william.expensemanagerapi.domain.entities.ExpenseCategory;

public interface LoadExpenseCategories {
  public List<ExpenseCategory> load();
  public Page<ExpenseCategory> load(Pageable pageable);
}
