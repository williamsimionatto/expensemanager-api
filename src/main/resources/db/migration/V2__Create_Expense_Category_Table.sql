
CREATE TABLE IF NOT EXISTS expense_category (
  id SERIAL NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);
