package william.expensemanagerapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import william.expensemanagerapi.domain.entities.Expense;
import william.expensemanagerapi.domain.entities.ExpenseCategory;
import william.expensemanagerapi.domain.entities.Period;
import william.expensemanagerapi.domain.entities.PeriodCategory;
import william.expensemanagerapi.domain.model.AddPeriodCategoryModel;
import william.expensemanagerapi.domain.model.AddPeriodModel;
import william.expensemanagerapi.domain.model.PeriodReport;
import william.expensemanagerapi.domain.usecases.period.AddPeriod;
import william.expensemanagerapi.domain.usecases.period.HasPeriodInSameDates;
import william.expensemanagerapi.repository.ExpenseRepository;
import william.expensemanagerapi.repository.PeriodCategoryRepository;
import william.expensemanagerapi.repository.PeriodRepository;

@Service
public class PeriodService implements 
  AddPeriod,
  HasPeriodInSameDates {
  @Autowired
  private PeriodRepository periodRepository;

  @Autowired
  private PeriodCategoryRepository periodCategoryRepository;

  @Autowired 
  private ExpenseCategoryService expenseCategoryService;

  @Autowired
  private ExpenseRepository expenseRepository;

  @Override
  public Period add(AddPeriodModel params) {
    if (params.getStartDate().after(params.getEndDate())) {
      throw new IllegalArgumentException("Start date cannot be after end date");
    }

    boolean hasPeriodInSameDates = this.hasPeriodInSameDates(params.getStartDate(), params.getEndDate());
    if (hasPeriodInSameDates) {
      throw new IllegalArgumentException("There is already a period in the same dates, please choose another dates");
    }

    Period period = new Period(params);
    period = periodRepository.save(period);

    if (!this.hasBudgetLimit(period.getBudget(), params.getCategories())) {
      throw new IllegalArgumentException("Budget limit exceeded");
    }

    for (AddPeriodCategoryModel category : params.getCategories()) {
      ExpenseCategory expenseCategory = expenseCategoryService.get(category.getCategory().getId());
      PeriodCategory periodCategory = new PeriodCategory(period, expenseCategory, category.getBudget());
      periodCategoryRepository.save(periodCategory);
    }

    return this.get(period.getId());
  }

  public Page<PeriodReport> getAll(Pageable pageable) {
    Page<Period> periods = periodRepository.findAll(pageable);

    Page<PeriodReport> periodReports = periods.map(period -> {
      Double totalReservedBudget = periodCategoryRepository.getTotalReservedBudget(period.getId());
      Double remainingBudget = period.getBudget() - totalReservedBudget;
      Double totalUsedBudget = expenseRepository.totalBugetUsed(period.getId());
      Double remainingUsedBudget = totalReservedBudget - totalUsedBudget;

      return new PeriodReport(
        period.getId(),
        period.getName(),
        period.getStartDate(),
        period.getEndDate(),
        period.getBudget(),
        totalReservedBudget,
        remainingBudget,
        totalUsedBudget,
        remainingUsedBudget
      );
    });

    return periodReports;
  }

  public List<PeriodReport> getAll() {
    List<Period> periods = periodRepository.findAll();

    List<PeriodReport> periodReports = periods.stream().map(period -> {
      Double totalReservedBudget = periodCategoryRepository.getTotalReservedBudget(period.getId());
      Double remainingBudget = period.getBudget() - totalReservedBudget;
      Double totalUsedBudget = expenseRepository.totalBugetUsed(period.getId());
      Double remainingUsedBudget = totalReservedBudget - totalUsedBudget;

      return new PeriodReport(
        period.getId(),
        period.getName(),
        period.getStartDate(),
        period.getEndDate(),
        period.getBudget(),
        totalReservedBudget,
        remainingBudget,
        totalUsedBudget,
        remainingUsedBudget
      );
    }).toList();

    return periodReports;
  }

  public Period get(Long id) {
    Period period = periodRepository.findById(id).orElse(null);
    return period;
  }

  public List<PeriodCategory> getPeriodCategories(Long periodId) {
    List<PeriodCategory> periodCategories = periodCategoryRepository.findByPeriodId(periodId);
    return periodCategories;
  }

  @Transactional
  public Period update(Long id, AddPeriodModel params) {
    Period period = this.get(id);

    if (!this.hasBudgetLimit(params.getBudget(), params.getCategories())) {
      throw new IllegalArgumentException("Budget limit exceeded");
    }

    Double totalBugetUsedInPeriod = expenseRepository.totalBugetUsed(period.getId());

    if (totalBugetUsedInPeriod > params.getBudget()) {
      throw new IllegalArgumentException("You cannot set a budget lower than the total used budget for the period: " + period.getName());
    }

    period.setName(params.getName());
    period.setStartDate(params.getStartDate());
    period.setEndDate(params.getEndDate());
    period.setBudget(params.getBudget());
    period = periodRepository.save(period);

    for (AddPeriodCategoryModel category : params.getCategories()) {
      PeriodCategory periodCategory = periodCategoryRepository.findByPeriodIdAndCategoryId(period.getId(), category.getCategory().getId());
      if (periodCategory == null) {
        ExpenseCategory expenseCategory = expenseCategoryService.get(category.getCategory().getId());
        periodCategory = new PeriodCategory(period, expenseCategory, category.getBudget());
        periodCategoryRepository.save(periodCategory);
      } else {
        Double totalBugetUsedInCategory = expenseRepository.totalBugetUsed(period.getId(), periodCategory.getCategory().getId());

        if (totalBugetUsedInCategory > category.getBudget()) {
          throw new IllegalArgumentException("You cannot set a budget lower than the total used budget for the category: " + periodCategory.getCategory().getName());
        }

        periodCategory.setBudget(category.getBudget());
        periodCategoryRepository.save(periodCategory);
      }
    }

    return period;
  }

  @Transactional
  public void deleteCategory(Long periodId, Long categoryId) {
    PeriodCategory periodCategory = periodCategoryRepository.findByPeriodIdAndCategoryId(periodId, categoryId);
    if (periodCategory == null) {
      throw new IllegalArgumentException("Period category not found");
    }

    List<Expense> expenses = expenseRepository.findAllByPeriodIdAndCategoryId(periodId, categoryId);
    // for (Expense expense : expenses) {
    //   expenseRepository.delete(expense);
    // }

    expenseRepository.deleteAll(expenses);
    expenseRepository.flush();
    periodCategoryRepository.deleteById(periodCategory.getId());
  }

  @Override
  public boolean hasPeriodInSameDates(Date startDate, Date endDate) {
    List<Period> periods = periodRepository.findAllByStartDateBetween(startDate, endDate);
    return periods.size() > 0;
  }

  private boolean hasBudgetLimit(Double budget, List<AddPeriodCategoryModel> categories) {
    Double totalBudget = categories.stream().mapToDouble(AddPeriodCategoryModel::getBudget).sum();
    return totalBudget <= budget;
  }
}
