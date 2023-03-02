package william.expensemanagerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.expensemanagerapi.domain.entities.ExpenseCategory;
import william.expensemanagerapi.domain.model.AddExpenseCategoryModel;
import william.expensemanagerapi.domain.usecases.expensecategory.AddExpenseCategory;
import william.expensemanagerapi.domain.usecases.expensecategory.LoadExpenseCategories;
import william.expensemanagerapi.repository.ExpenseCategoryRepository;

@Service
public class ExpenseCategoryService implements 
  AddExpenseCategory,
  LoadExpenseCategories {
  @Autowired
  private ExpenseCategoryRepository expenseCategoryRepository;

  @Override
  public ExpenseCategory add(AddExpenseCategoryModel params) {
    ExpenseCategory expenseCategory = new ExpenseCategory(params);
    return expenseCategoryRepository.save(expenseCategory);
  }

  @Override
  public List<ExpenseCategory> load() {
    return expenseCategoryRepository.findAll();
  }
}
