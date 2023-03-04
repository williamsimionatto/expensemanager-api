package william.expensemanagerapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.expensemanagerapi.domain.entities.Period;
import william.expensemanagerapi.domain.model.AddPeriodModel;
import william.expensemanagerapi.domain.usecases.period.AddPeriod;
import william.expensemanagerapi.domain.usecases.period.HasPeriodInSameDates;
import william.expensemanagerapi.repository.PeriodRepository;

@Service
public class PeriodService implements 
  AddPeriod,
  HasPeriodInSameDates {
  @Autowired
  private PeriodRepository periodRepository;

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
    return periodRepository.save(period);
  }

  @Override
  public boolean hasPeriodInSameDates(Date startDate, Date endDate) {
    List<Period> periods = periodRepository.findAllByStartDateBetween(startDate, endDate);
    return periods.size() > 0;
  }
}
