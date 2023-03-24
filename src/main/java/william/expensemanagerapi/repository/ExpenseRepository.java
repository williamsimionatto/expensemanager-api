package william.expensemanagerapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import william.expensemanagerapi.domain.entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
  @Query(
    value = "SELECT COALESCE(SUM(amount), 0) FROM expense WHERE period_id = :periodId",
    nativeQuery = true
  )
  public Double totalBugetUsed(@Param("periodId") Long periodId);

  @Query(
    value = "SELECT COALESCE(SUM(amount), 0) FROM expense WHERE period_id = :periodId AND category_id = :categoryId",
    nativeQuery = true
  )
  public Double totalBugetUsed(
    @Param("periodId") Long periodId,
    @Param("categoryId") Long categoryId
  );

  @Query(
    value = "SELECT * FROM expense WHERE period_id = :periodId AND category_id = :categoryId",
    nativeQuery = true
  )
  public List<Expense> findAllByPeriodIdAndCategoryId(Long periodId, Long categoryId);

  public List<Expense> findAllByOrderByDateDesc();
}
