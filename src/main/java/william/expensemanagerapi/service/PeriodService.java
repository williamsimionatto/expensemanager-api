package william.expensemanagerapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.expensemanagerapi.domain.entities.Period;
import william.expensemanagerapi.domain.entities.PeriodCategory;
import william.expensemanagerapi.domain.model.AddPeriodCategoryModel;
import william.expensemanagerapi.domain.model.AddPeriodModel;
import william.expensemanagerapi.domain.usecases.period.AddPeriod;
import william.expensemanagerapi.domain.usecases.period.HasPeriodInSameDates;
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

    for (AddPeriodCategoryModel category : params.getCategories()) {
      PeriodCategory periodCategory = new PeriodCategory(period.getId(), category.getCategoryId(), category.getBudget());
      periodCategoryRepository.save(periodCategory);
    }

    return get(period.getId());
  }

  public Period get(Long id) {
    return periodRepository.findById(id).orElse(null);
  }

  @Override
  public boolean hasPeriodInSameDates(Date startDate, Date endDate) {
    List<Period> periods = periodRepository.findAllByStartDateBetween(startDate, endDate);
    return periods.size() > 0;
  }
}
