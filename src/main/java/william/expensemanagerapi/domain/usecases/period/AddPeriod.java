package william.expensemanagerapi.domain.usecases.period;

import william.expensemanagerapi.domain.entities.Period;
import william.expensemanagerapi.domain.model.AddPeriodModel;

public interface AddPeriod {
  public Period add(AddPeriodModel params);
}
