package william.expensemanagerapi.domain.entities;

import java.io.Serializable;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.OneToMany;

import jakarta.persistence.FetchType;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "period_id", referencedColumnName = "id")
  @JsonBackReference
  private Period period;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "expense_category_id", referencedColumnName = "id")
  private ExpenseCategory category;

  @OneToMany(cascade = CascadeType.DETACH)
  @JoinColumns({
    @JoinColumn(name = "period_id", referencedColumnName = "period_id"),
    @JoinColumn(name = "category_id", referencedColumnName = "expense_category_id")
  })
  private List<Expense> expenses;

  @NonNull
  @Column(name = "budget")
  private Double budget;

  public PeriodCategory(Period period, ExpenseCategory category, Double budget) {
    this.period = period;
    this.category = category;
    this.budget = budget;
  }
}
