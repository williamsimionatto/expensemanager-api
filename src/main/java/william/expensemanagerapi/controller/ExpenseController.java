package william.expensemanagerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import william.expensemanagerapi.domain.entities.Expense;
import william.expensemanagerapi.domain.model.AddExpenseModel;
import william.expensemanagerapi.service.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
  @Autowired
  private ExpenseService expenseService;

  @PostMapping
  public ResponseEntity<Expense> create(
    @Valid @RequestBody AddExpenseModel params
  ) {
    return ResponseEntity.ok(expenseService.add(params));
  }
}
