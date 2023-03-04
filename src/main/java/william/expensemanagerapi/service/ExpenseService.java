package william.expensemanagerapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.expensemanagerapi.domain.entities.Expense;
import william.expensemanagerapi.domain.entities.Period;
import william.expensemanagerapi.domain.entities.PeriodCategory;
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
  private PeriodService periodService;

  @Autowired
  private PeriodCategoryService periodCategoryService;

  @Override
  public Expense add(AddExpenseModel params) {
    PeriodCategory periodCategory = periodCategoryService.get(params.getPeriodId(), params.getCategoryId());
    Period period = periodService.get(params.getPeriodId());

    Expense expense = new Expense(params, period, periodCategory.getCategory());
    Double totalBugetUsedInPeriod = this.totalBugetUsed(period.getId());
    Double totalBugetUsedInCategory = this.totalBugetUsed(period.getId(), periodCategory.getCategory().getId());

    if (totalBugetUsedInCategory + expense.getAmount() > periodCategory.getBudget()) {
      throw new IllegalArgumentException("Budget Category limit exceeded");
    } else if (totalBugetUsedInPeriod + expense.getAmount() > period.getBudget()) {
      throw new IllegalArgumentException("Budget Period limit exceeded");
    }

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
