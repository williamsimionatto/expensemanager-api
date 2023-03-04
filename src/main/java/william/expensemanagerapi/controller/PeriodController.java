package william.expensemanagerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import william.expensemanagerapi.domain.entities.Period;
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
  public ResponseEntity<Iterable<PeriodReport>> getAll() {
    return ResponseEntity.ok(periodService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Period> get(@PathVariable Long id) {
    return ResponseEntity.ok(periodService.get(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Period> update(
    @PathVariable Long id,
    @Valid @RequestBody AddPeriodModel params
  ) {
    return ResponseEntity.ok(periodService.update(id, params));
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
