package william.expensemanagerapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import william.expensemanagerapi.domain.entities.ExpenseCategory;
import william.expensemanagerapi.domain.model.AddExpenseCategoryModel;
import william.expensemanagerapi.service.ExpenseCategoryService;

@RestController
@RequestMapping("/expense-category")
public class ExpenseCategoryController {
  @Autowired
  private ExpenseCategoryService expenseCategoryService;

  @PostMapping
  public ResponseEntity<ExpenseCategory> create(
    @Valid @RequestBody AddExpenseCategoryModel params,
    Errors errors
  ) {
    if (errors.hasErrors()) {
      List<FieldError> fieldErrors = errors.getFieldErrors();
      List<String> errorMessages = fieldErrors.stream()
        .map(fieldError -> fieldError.getDefaultMessage())
        .collect(Collectors.toList());

      System.out.println(errorMessages);
      return ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok(expenseCategoryService.add(params));
  }
}