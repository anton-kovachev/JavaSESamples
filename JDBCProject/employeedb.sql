SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `employeeschema` DEFAULT CHARACTER SET latin1 ;
USE `employeeschema` ;

-- -----------------------------------------------------
-- Table `employeeschema`.`employer`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `employeeschema`.`employer` (
  `idEmployer` INT(11) NOT NULL AUTO_INCREMENT ,
  `FirstName` VARCHAR(25) NOT NULL ,
  `LastName` VARCHAR(25) NOT NULL ,
  `Salary` INT(11) NOT NULL ,
  `HireDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`idEmployer`) ,
  UNIQUE INDEX `idEmployer_UNIQUE` (`idEmployer` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
