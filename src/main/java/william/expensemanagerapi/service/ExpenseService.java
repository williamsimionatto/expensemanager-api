package william.expensemanagerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.expensemanagerapi.domain.entities.Expense;
import william.expensemanagerapi.domain.entities.Period;
import william.expensemanagerapi.domain.entities.PeriodCategory;
import william.expensemanagerapi.domain.model.AddExpenseModel;
import william.expensemanagerapi.domain.usecases.expense.AddExpense;
import william.expensemanagerapi.domain.usecases.expense.DeleteExpense;
import william.expensemanagerapi.domain.usecases.expense.TotalBugetUsed;
import william.expensemanagerapi.repository.ExpenseRepository;
import william.expensemanagerapi.validation.DateValidation;

@Service
public class ExpenseService implements 
  AddExpense,
  TotalBugetUsed,
  DeleteExpense {
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

    if (periodCategory == null) {
      throw new IllegalArgumentException("Period or Category are invalid!");
    }

    if (!DateValidation.isDateInPeriod(params.getDate(), period.getStartDate(), period.getEndDate())) {
      String startDate = DateValidation.formatDate(period.getStartDate(), "yyyy-MM-dd");
      String endDate = DateValidation.formatDate(period.getEndDate(), "yyyy-MM-dd");

      throw new IllegalArgumentException(
        "Date is not in the period: [" + startDate + " to " + endDate + "]"
      );
    }

    Expense expense = new Expense(params, period, periodCategory.getCategory());
    Double totalBugetUsedInPeriod = this.totalBugetUsed(period.getId());
    Double totalBugetUsedInCategory = this.totalBugetUsed(period.getId(), periodCategory.getCategory().getId());

    if (totalBugetUsedInPeriod + expense.getAmount() > period.getBudget()) {
      throw new IllegalArgumentException("Budget Period limit exceeded");
    } else if (totalBugetUsedInCategory + expense.getAmount() > periodCategory.getBudget()) {
      throw new IllegalArgumentException("Budget Category limit exceeded");
    }

    return expenseRepository.save(expense);
  }

  public List<Expense> getAll() {
    return expenseRepository.findAllByOrderByDateDesc();
  }

  @Override
  public Double totalBugetUsed(Long periodId, Long categoryId) {
    return expenseRepository.totalBugetUsed(periodId, categoryId);
  }

  @Override
  public Double totalBugetUsed(Long periodId) {
    return expenseRepository.totalBugetUsed(periodId);
  }

  @Override
  public void delete(Long id) {
    expenseRepository.deleteById(id);
  }
}
