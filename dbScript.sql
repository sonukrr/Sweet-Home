-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sweet-home
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sweet-home
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sweet-home` ;
USE `sweet-home` ;

-- -----------------------------------------------------
-- Table `sweet-home`.`booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sweet-home`.`booking` (
  `bookingId` INT NOT NULL AUTO_INCREMENT,
  `fromDate` DATE NULL,
  `toDate` VARCHAR(45) NULL,
  `aadharNumber` VARCHAR(45) NULL,
  `numOfRooms` VARCHAR(45) NULL,
  `roomNumbers` VARCHAR(45) NULL,
  `roomPrice` INT NOT NULL DEFAULT 1000,
  `bookingcol` INT NULL,
  `transactionId` INT NULL DEFAULT 0,
  `bookedOn` DATE NULL,
  PRIMARY KEY (`bookingId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sweet-home`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sweet-home`.`transaction` (
  `transactionId` INT NOT NULL AUTO_INCREMENT,
  `paymentMode` VARCHAR(45) NULL,
  `bookingId` INT NOT NULL,
  `upiId` VARCHAR(45) NULL,
  `cardNumber` VARCHAR(45) NULL,
  PRIMARY KEY (`transactionId`),
  INDEX `fk_transaction_1_idx` (`bookingId` ASC) VISIBLE,
  CONSTRAINT `fk_transaction_1`
    FOREIGN KEY (`bookingId`)
    REFERENCES `sweet-home`.`booking` (`bookingId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
