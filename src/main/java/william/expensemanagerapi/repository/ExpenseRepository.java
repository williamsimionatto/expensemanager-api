package william.expensemanagerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import william.expensemanagerapi.domain.entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
  @Query(
    value = "SELECT SUM(amount) FROM expense WHERE period_id = :periodId",
    nativeQuery = true
  )
  public Double getTotalUsedBudget(@Param("periodId") Long periodId);
}