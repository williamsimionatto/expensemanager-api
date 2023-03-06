package william.expensemanagerapi.repository;

import java.util.List;

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
    value = "SELECT COALESCE(SUM(budget), 0) FROM period_category WHERE period_id = :periodId",
    nativeQuery = true
  )
  public Double getTotalReservedBudget(@Param("periodId") Long periodId);

  @Query(
    value = 
      "SELECT period_category.* " + 
      "FROM expense_category " + 
      "JOIN period_category ON expense_category.id = period_category.expense_category_id " + 
      "WHERE period_category.period_id = :periodId",
    nativeQuery = true
  )
  public List<PeriodCategory> findByPeriodId(@Param("periodId") Long periodId);
}
