CREATE TABLE IF NOT EXISTS period_category (
  id SERIAL NOT NULL,
  period_id INT NOT NULL,
  expense_category_id INT NOT NULL,
  budget DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (period_id) REFERENCES period (id),
  FOREIGN KEY (expense_category_id) REFERENCES expense_category (id)
);