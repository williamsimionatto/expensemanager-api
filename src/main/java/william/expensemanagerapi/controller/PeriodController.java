package william.expensemanagerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import william.expensemanagerapi.domain.entities.Period;
import william.expensemanagerapi.domain.model.AddPeriodModel;
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
}