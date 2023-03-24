package william.expensemanagerapi.domain.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import william.expensemanagerapi.domain.model.AddExpenseModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "expense")
public class Expense {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  @JsonBackReference
  private ExpenseCategory expenseCategory;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "period_id", referencedColumnName = "id")
  @JsonBackReference
  private Period period;

  @NonNull
  @Column(name = "description")
  private String description;

  @NonNull
  @Column(name = "date")
  private Date date;

  @NonNull
  @Column(name = "amount")
  private Double amount;

  public Expense(
    AddExpenseModel params,
    Period period,
    ExpenseCategory expenseCategory
  ) {
    this.description = params.getDescription();
    this.date = params.getDate();
    this.amount = params.getAmount();
    this.period = period;
    this.expenseCategory = expenseCategory;
  }
}
