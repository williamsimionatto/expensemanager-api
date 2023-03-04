package william.expensemanagerapi.domain.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodReport {
  private Long id;
  private String name;
  private Date startDate;
  private Date endDate;
  private Double budget;
  private Double reservedBudget;
  private Double remainingBudget;
}
