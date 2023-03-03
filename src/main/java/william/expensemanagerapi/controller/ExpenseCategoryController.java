package william.expensemanagerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import william.expensemanagerapi.domain.entities.ExpenseCategory;
import william.expensemanagerapi.domain.model.AddExpenseCategoryModel;
import william.expensemanagerapi.domain.model.EditExpenseCategoryModel;
import william.expensemanagerapi.service.ExpenseCategoryService;

@RestController
@RequestMapping("/expense-category")
public class ExpenseCategoryController {
  @Autowired
  private ExpenseCategoryService expenseCategoryService;

  @PostMapping
  public ResponseEntity<ExpenseCategory> create(
    @Valid @RequestBody AddExpenseCategoryModel params
  ) {
    return ResponseEntity.ok(expenseCategoryService.add(params));
  }

  @GetMapping
  public ResponseEntity<Page<ExpenseCategory>> load(
    @RequestParam(defaultValue = "0") int page, 
    @RequestParam(defaultValue = "10") int size
  ) {
    Pageable pageable = PageRequest.of(page, size);
    Page<ExpenseCategory> expenseCategories = expenseCategoryService.load(pageable);
    return ResponseEntity.ok(expenseCategories);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExpenseCategory> get(@PathVariable Long id) {
    ExpenseCategory expenseCategory = expenseCategoryService.get(id);
    return ResponseEntity.ok(expenseCategory);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ExpenseCategory> edit(
    @PathVariable Long id,
    @RequestBody @Valid EditExpenseCategoryModel params
  ) {
    ExpenseCategory expenseCategory = expenseCategoryService.edit(id, params);
    return ResponseEntity.ok(expenseCategory);
  }
}
