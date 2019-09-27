CREATE SCHEMA `ishop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `ishop`.`items` (
                                        `idItem` INT NOT NULL AUTO_INCREMENT,
                                        `name` VARCHAR(225) NOT NULL,
                                        `price` DECIMAL(6,2) NOT NULL,
                                        `model` VARCHAR(225) NOT NULL,
                                        PRIMARY KEY (`idItem`));

INSERT INTO `ishop`. `items` ('name', 'price',model) VALUES ('iPhone 11', '1000','XS');