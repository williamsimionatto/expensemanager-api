package william.expensemanagerapi.domain.usecases.period;

import java.util.Date;

public interface HasPeriodInSameDates {
  public boolean hasPeriodInSameDates(Date startDate, Date endDate);
}
