CREATE TABLE IF NOT EXISTS `books_catalog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `file_name` varchar(250) NULL,
  `document_format` varchar(250) NULL,
  `size` BIGINT null,
  `upload_dir` varchar(250) NULL,
  `price` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`)) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `users_catalog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `purchase_order` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `book_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    `date` TIMESTAMP(3) NOT NULL,
    `total` INT,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_book_id`
        FOREIGN KEY (`book_id`)
        REFERENCES `books_catalog` (`id`)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT `fk_user_id`
            FOREIGN KEY (`user_id`)
            REFERENCES `users_catalog` (`id`)
            ON UPDATE CASCADE ON DELETE RESTRICT)
    ENGINE = InnoDB;