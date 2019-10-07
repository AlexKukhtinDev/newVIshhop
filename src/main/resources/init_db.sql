CREATE SCHEMA 'internetshop' DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internetshop`.`items` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `model` VARCHAR(255) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`item_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `internetshop`.`items` (`name`, `model`, `price`) VALUES ('Macbook', 'sumg', '1200');
INSERT INTO `internetshop`.`items` (`name`, `model`, `price`) VALUES ('iPhone', 'xs', '1000');
INSERT INTO `internetshop`.`items` (`name`, `model`, `price`) VALUES ('Simens', 's5', '100');
INSERT INTO `internetshop`.`items` (`name`, `model`, `price`) VALUES ('Nokia', 'n11', '100');

INSERT INTO `internetshop`.`users` (`name`, `surname`, `login`, `password`)
VALUES ('Alex', 'Kukhtin', 'Cooper', '12');
INSERT INTO `internetshop`.`users` (`name`, `surname`, `login`, `password`)
VALUES ('Vasya', 'Pupcin', 'user', '12');

INSERT INTO `internetshop`.`orders` (`user_id`) VALUES ('1');
INSERT INTO `internetshop`.`orders` (`user_id`) VALUES ('1');
INSERT INTO `internetshop`.`orders` (`user_id`) VALUES ('2');

INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('1', '1');
INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('1', '4');
INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('2', '1');
INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('2', '2');
INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('3', '3');