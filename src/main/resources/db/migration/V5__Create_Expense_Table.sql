CREATE TABLE IF NOT EXISTS `expense` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `expense_category_id` INT(11) NOT NULL,
  `amount` DECIMAL(10,2) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`expense_category_id`) REFERENCES `expense_category` (`id`)
);