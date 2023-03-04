package william.expensemanagerapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.expensemanagerapi.domain.entities.Expense;
import william.expensemanagerapi.domain.entities.ExpenseCategory;
import william.expensemanagerapi.domain.entities.Period;
import william.expensemanagerapi.domain.model.AddExpenseModel;
import william.expensemanagerapi.domain.usecases.expense.AddExpense;
import william.expensemanagerapi.domain.usecases.expense.TotalBugetUsed;
import william.expensemanagerapi.repository.ExpenseRepository;

@Service
public class ExpenseService implements 
  AddExpense,
  TotalBugetUsed {
  @Autowired
  private ExpenseRepository expenseRepository;

  @Autowired
  private ExpenseCategoryService expenseCategoryService;

  @Autowired
  private PeriodService periodService;

  @Override
  public Expense add(AddExpenseModel params) {
    ExpenseCategory expenseCategory = expenseCategoryService.get(params.getCategoryId());
    Period period = periodService.get(params.getPeriodId());

    Expense expense = new Expense(params, period, expenseCategory);
    return expenseRepository.save(expense);
  }

  @Override
  public Double totalBugetUsed(Long periodId, Long categoryId) {
    return expenseRepository.totalBugetUsed(periodId, categoryId);
  }

  @Override
  public Double totalBugetUsed(Long periodId) {
    return expenseRepository.totalBugetUsed(periodId);
  }
}
