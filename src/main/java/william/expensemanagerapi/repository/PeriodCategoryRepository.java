package william.expensemanagerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import william.expensemanagerapi.domain.entities.PeriodCategory;

@Repository
public interface PeriodCategoryRepository extends JpaRepository<PeriodCategory, Long> {
}
