CREATE TABLE IF NOT EXISTS expense (
  id SERIAL NOT NULL,
  period_id INT NOT NULL,
  category_id INT NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  description VARCHAR(255) NOT NULL,
  date DATE NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (period_id) REFERENCES period (id),
  FOREIGN KEY (category_id) REFERENCES expense_category (id) ON DELETE CASCADE
);