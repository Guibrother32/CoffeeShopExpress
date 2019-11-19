DROP SCHEMA IF EXISTS CoffeeShopDB;

CREATE SCHEMA IF NOT EXISTS `CoffeeShopDB` DEFAULT CHARACTER SET utf8 ;
USE `CoffeeShopDB` ;

-- -----------------------------------------------------
-- Table `CoffeeShopDB`.`Compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoffeeShopDB`.`Compra` (
  `idCompra` INT NOT NULL,
  `NumCafe1` INT NOT NULL,
  `NumCafe2` INT NOT NULL,
  `NumCafe3` INT NOT NULL,
  PRIMARY KEY (`idCompra`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoffeeShopDB`.`Estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoffeeShopDB`.`Estoque` (
  `category_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`));


-- -----------------------------------------------------
-- Table `CoffeeShopDB`.`Produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS Produto;
CREATE TABLE IF NOT EXISTS `CoffeeShopDB`.`Produto` (
  `idProduto` INT auto_increment NOT NULL,
  `nome` VARCHAR(30) NOT NULL,
  `preco` DOUBLE NOT NULL,
  `Qtd` INT NOT NULL,
  `Estoque_category_id` INT,
  PRIMARY KEY (`idProduto`),
  INDEX `fk_Produto_Estoque1_idx` (`Estoque_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_Estoque1`
    FOREIGN KEY (`Estoque_category_id`)
    REFERENCES `CoffeeShopDB`.`Estoque` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoffeeShopDB`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoffeeShopDB`.`Cliente` (
  `idCliente` INT NOT NULL,
  `Produto_idProduto` INT NOT NULL,
  PRIMARY KEY (`idCliente`),
  INDEX `fk_Cliente_Produto1_idx` (`Produto_idProduto` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_Produto1`
    FOREIGN KEY (`Produto_idProduto`)
    REFERENCES `CoffeeShopDB`.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoffeeShopDB`.`Cliente_Efetua_Compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoffeeShopDB`.`Cliente_Efetua_Compra` (
  `Cliente_idCliente` INT NOT NULL,
  `Compra_idCompra` INT NOT NULL,
  PRIMARY KEY (`Cliente_idCliente`, `Compra_idCompra`),
  INDEX `fk_Cliente_has_Compra_Compra1_idx` (`Compra_idCompra` ASC) VISIBLE,
  INDEX `fk_Cliente_has_Compra_Cliente1_idx` (`Cliente_idCliente` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_has_Compra_Cliente1`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `CoffeeShopDB`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cliente_has_Compra_Compra1`
    FOREIGN KEY (`Compra_idCompra`)
    REFERENCES `CoffeeShopDB`.`Compra` (`idCompra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoffeeShopDB`.`Gerente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoffeeShopDB`.`Gerente` (
  `category_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `Estoque_category_id` INT NOT NULL,
  PRIMARY KEY (`category_id`),
  INDEX `fk_Gerente_Estoque1_idx` (`Estoque_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_Gerente_Estoque1`
    FOREIGN KEY (`Estoque_category_id`)
    REFERENCES `CoffeeShopDB`.`Estoque` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SELECT * FROM Produto;
