package william.expensemanagerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import william.expensemanagerapi.domain.entities.Period;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {
}
