package william.expensemanagerapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import william.expensemanagerapi.domain.entities.Period;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {
  @Query(
    value = "SELECT * FROM period WHERE start_date BETWEEN :startDate AND :endDate OR end_date BETWEEN :startDate AND :endDate",
    countQuery = "SELECT COUNT(*) FROM period WHERE start_date BETWEEN :startDate AND :endDate OR end_date BETWEEN :startDate AND :endDate",
    nativeQuery = true
  )
  public List<Period> findAllByStartDateBetween(
    @Param("startDate") Date startDate, 
    @Param("endDate") Date endDate
  );
}
