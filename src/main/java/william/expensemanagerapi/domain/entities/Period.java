package william.expensemanagerapi.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import william.expensemanagerapi.domain.model.AddPeriodModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "period")
@EnableJpaRepositories
public class Period implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NonNull
  @Column(name = "name")
  private String name;

  @NonNull
  @Column(name = "start_date")
  private Date startDate;

  @NonNull
  @Column(name = "end_date")
  private Date endDate;

  @NonNull
  @Column(name = "budget")
  private Double budget;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "periodId")
  private List<PeriodCategory> periodCategories = new ArrayList<PeriodCategory>();

  public Period(AddPeriodModel params) {
    this.name = params.getName();
    this.startDate = params.getStartDate();
    this.endDate = params.getEndDate();
    this.budget = params.getBudget();
  }
}
