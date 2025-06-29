-- Create and use database
CREATE SCHEMA IF NOT EXISTS `jj_apartments` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `jj_apartments` ;


-- Disable checks temporarily (for safe table creation with foreign keys)
SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- Dropping of tables 
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS units;
DROP TABLE IF EXISTS tenants;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS utilities;
DROP TABLE IF EXISTS rates;
DROP TABLE IF EXISTS expenses;
DROP TABLE IF EXISTS monthly_reports;


-- -------------------------
-- Table: users
-- -------------------------
CREATE TABLE IF NOT EXISTS users (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  full_name VARCHAR(45) NULL DEFAULT NULL,
  email VARCHAR(45) NOT NULL,
  is_owner TINYINT NULL DEFAULT NULL,
  created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE INDEX username_UNIQUE (username ASC),
  UNIQUE INDEX email_UNIQUE (email ASC)
) ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `jj_apartments`.`units`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jj_apartments`.`units` (
  `id` INT NOT NULL,
  `unit_number` VARCHAR(1) NULL,
  `is_occupied` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -------------------------
-- Table: tenants
-- -------------------------
CREATE TABLE IF NOT EXISTS `jj_apartments`.`tenants` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `middle_initial` VARCHAR(1) NULL DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(15) NOT NULL,
  `units_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE,
  INDEX `fk_tenants_units1_idx` (`units_id` ASC) VISIBLE,
  CONSTRAINT `fk_tenants_units1`
    FOREIGN KEY (`units_id`)
    REFERENCES `jj_apartments`.`units` (`id`)
) ENGINE = InnoDB;


-- -------------------------
-- Table: payments
-- -------------------------
CREATE TABLE IF NOT EXISTS payments (
  id INT NOT NULL AUTO_INCREMENT,
  tenant_id INT NOT NULL,
  reason VARCHAR(45) NOT NULL,
  mode_of_payment VARCHAR(45) NULL DEFAULT NULL,
  amount DECIMAL(10,2) NOT NULL,
  due_date DATE NULL DEFAULT NULL,
  month_of_start DATE NULL DEFAULT NULL,
  month_of_end DATE NULL DEFAULT NULL,
  is_paid TINYINT NOT NULL,
  paid_at DATE NULL DEFAULT NULL,
  PRIMARY KEY (id),
  INDEX tenant_id_idx (tenant_id ASC),
  CONSTRAINT tenant_id FOREIGN KEY (tenant_id) REFERENCES tenants (id)
) ENGINE = InnoDB;

-- -------------------------
-- Table: rates
-- -------------------------
CREATE TABLE IF NOT EXISTS rates (
  id INT NOT NULL AUTO_INCREMENT,
  type VARCHAR(45) NOT NULL,
  rate DECIMAL(10,2) NOT NULL,
  date DATE NOT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB;


-- -------------------------
-- Table: utilities
-- -------------------------
CREATE TABLE IF NOT EXISTS utilities (
  id INT NOT NULL AUTO_INCREMENT,
  type VARCHAR(45) NOT NULL,
  previous_reading DECIMAL(10,2) NULL DEFAULT NULL,
  current_reading DECIMAL(10,2) NOT NULL,
  total_meter DECIMAL(10,2) NOT NULL,
  total_amount DECIMAL(10,2) NOT NULL,
  due_date DATE NULL DEFAULT NULL,
  month_of_start DATE NOT NULL,
  month_of_end DATE NOT NULL,
  is_paid TINYINT NULL,
  paid_at DATE NULL DEFAULT NULL,
  tenants_id INT NOT NULL,
  rates_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_meralco_tenants1_idx (tenants_id ASC),
  INDEX fk_utilities_rates1_idx (rates_id ASC),
  CONSTRAINT fk_meralco_tenants1 FOREIGN KEY (tenants_id) REFERENCES tenants (id),
  CONSTRAINT fk_utilities_rates1 FOREIGN KEY (rates_id) REFERENCES rates (id)
) ENGINE = InnoDB;


-- -------------------------
-- Table: expenses
-- -------------------------
CREATE TABLE IF NOT EXISTS expenses (
  id INT NOT NULL AUTO_INCREMENT,
  amount DECIMAL(10,2) NOT NULL,
  reason VARCHAR(45) NOT NULL,
  date DATE NOT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB;

-- -------------------------
-- Table: monthly_reports
-- -------------------------
CREATE TABLE IF NOT EXISTS monthly_reports (
  id INT NOT NULL AUTO_INCREMENT,
  year INT NOT NULL,
  month INT NOT NULL,
  total_earnings DECIMAL(10,2) NOT NULL,
  total_expenses DECIMAL(10,2) NOT NULL,
  net_income DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB;


-- Restore original SQL modes and checks
SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
