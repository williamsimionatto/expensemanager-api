package william.expensemanagerapi.service;

import java.util.Date;

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
    boolean hasPeriodInSameDates = this.hasPeriodInSameDates(params.getStartDate(), params.getEndDate());
    System.out.println(hasPeriodInSameDates);
    if (hasPeriodInSameDates) {
      throw new IllegalArgumentException("There is already a period in the same dates, please choose another dates");
    }

    Period period = new Period(params);
    return periodRepository.save(period);
  }

  @Override
  public boolean hasPeriodInSameDates(Date startDate, Date endDate) {
    return periodRepository.findAllByStartDateBetween(startDate, endDate).size() > 0;
  }
}
