package william.expensemanagerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import william.expensemanagerapi.domain.entities.Period;
import william.expensemanagerapi.domain.entities.PeriodCategory;
import william.expensemanagerapi.domain.model.AddPeriodModel;
import william.expensemanagerapi.domain.model.PeriodReport;
import william.expensemanagerapi.service.PeriodService;

@RestController
@RequestMapping("/period")
public class PeriodController {
  @Autowired
  private PeriodService periodService;

  @PostMapping
  public ResponseEntity<Period> create(
    @Valid @RequestBody AddPeriodModel params
  ) {
    return ResponseEntity.ok(periodService.add(params));
  }

  @GetMapping
  public ResponseEntity<Page<PeriodReport>> getAll(
    @RequestParam(defaultValue = "0") int page, 
    @RequestParam(defaultValue = "10") int size
  ) {
    Pageable pageable = PageRequest.of(page, size);
    Page<PeriodReport> periodReports = periodService.getAll(pageable);

    return ResponseEntity.ok(periodReports);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Period> get(@PathVariable Long id) {
    return ResponseEntity.ok(periodService.get(id));
  }

  @GetMapping("/{id}/category")
  public ResponseEntity<List<PeriodCategory>> getCategories(@PathVariable Long id) {
    return ResponseEntity.ok(periodService.getPeriodCategories(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(
    @PathVariable Long id,
    @Valid @RequestBody AddPeriodModel params
  ) {
    Period period = periodService.update(id, params);
    return ResponseEntity.ok(period);
  }

  @DeleteMapping("/{id}/category/{categoryId}")
  public ResponseEntity<Period> deleteCategory(
    @PathVariable Long id,
    @PathVariable Long categoryId
  ) {
    periodService.deleteCategory(id, categoryId);
    return new ResponseEntity<Period>(HttpStatus.NO_CONTENT);
  }
}
