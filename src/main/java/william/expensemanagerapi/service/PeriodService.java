package william.expensemanagerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.expensemanagerapi.domain.entities.Period;
import william.expensemanagerapi.domain.model.AddPeriodModel;
import william.expensemanagerapi.domain.usecases.period.AddPeriod;
import william.expensemanagerapi.repository.PeriodRepository;

@Service
public class PeriodService implements AddPeriod {
  @Autowired
  private PeriodRepository periodRepository;

  @Override
  public Period add(AddPeriodModel params) {
    Period period = new Period(params);
    return periodRepository.save(period);
  }
}
