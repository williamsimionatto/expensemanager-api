package william.expensemanagerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.expensemanagerapi.domain.entities.PeriodCategory;
import william.expensemanagerapi.repository.PeriodCategoryRepository;

@Service
public class PeriodCategoryService {
  @Autowired
  private PeriodCategoryRepository periodCategoryRepository;

  public PeriodCategory get(Long periodId, Long categoryId) {
    return periodCategoryRepository.findByPeriodIdAndCategoryId(periodId, categoryId);
  }
}
