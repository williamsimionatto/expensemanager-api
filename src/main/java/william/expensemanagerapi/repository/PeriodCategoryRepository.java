package william.expensemanagerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import william.expensemanagerapi.domain.entities.PeriodCategory;

@Repository
public interface PeriodCategoryRepository extends JpaRepository<PeriodCategory, Long> {
  @Query(
    value = "SELECT * FROM period_category WHERE period_id = :periodId AND expense_category_id = :categoryId",
    nativeQuery = true
  )
  public PeriodCategory findByPeriodIdAndCategoryId(
    @Param("periodId") Long periodId,
    @Param("categoryId") Long categoryId
  );

  @Query(
    value = "SELECT SUM(budget) FROM period_category WHERE period_id = :periodId",
    nativeQuery = true
  )
  public Double getTotalReservedBudget(@Param("periodId") Long periodId);
}
