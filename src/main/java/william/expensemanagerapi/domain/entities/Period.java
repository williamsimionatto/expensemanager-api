package william.expensemanagerapi.domain.entities;

import java.io.Serializable;
import java.util.Date;

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
@Entity(name = "period")
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
}
