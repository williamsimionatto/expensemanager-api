package william.expensemanagerapi.domain.usecases.expense;

public interface TotalBugetUsed {
  public Double totalBugetUsed(Long periodId);
  public Double totalBugetUsed(Long periodId, Long categoryId);
}
