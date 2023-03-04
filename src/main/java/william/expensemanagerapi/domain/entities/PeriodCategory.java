package william.expensemanagerapi.domain.entities;

import java.io.Serializable;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

  @Column(name = "expense_category_id")
  private Long categoryId;

  @NonNull
  @Column(name = "budget")
  private Double budget;

  public PeriodCategory(Long periodId, Long categoryId, Double budget) {
    this.periodId = periodId;
    this.categoryId = categoryId;
    this.budget = budget;
  }
}
