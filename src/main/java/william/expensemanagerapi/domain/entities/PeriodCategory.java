package william.expensemanagerapi.domain.entities;

import java.io.Serializable;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "period_category")
public class PeriodCategory implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "period_id")
  private Long periodId;

  @ManyToOne()
  @JoinColumn(name="expense_category_id", nullable=false)
  private ExpenseCategory expenseCategory;

  @NonNull
  @Column(name = "budget")
  private Double budget;

  public PeriodCategory(Long periodId, ExpenseCategory expenseCategory, Double budget) {
    this.periodId = periodId;
    this.expenseCategory = expenseCategory;
    this.budget = budget;
  }
}
