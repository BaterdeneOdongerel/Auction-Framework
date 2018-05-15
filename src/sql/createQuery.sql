create database auction_system;
use auction_system;

CREATE TABLE IF NOT EXISTS `User` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(100) UNIQUE,
	`user_name` VARCHAR(20),
	`first_name` VARCHAR(100),
	`last_name` VARCHAR(100),
	`password` VARCHAR(20),
	PRIMARY KEY (`id`)
) ENGINE MyISAM DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

insert into User(`email`, `user_name`, `first_name`, `last_name`, `password`) values ('vanthuyphan@gmail.com', 'vanthuyphan', 'van', 'phan', '123456');
insert into User(`email`, `user_name`, `first_name`, `last_name`, `password`) values ('bati@gmail.com', 'bati', 'bati', 'bati', '123456');
insert into User(`email`, `user_name`, `first_name`, `last_name`, `password`) values ('lukman@gmail.com', 'lukman', 'lukman', 'lukmna', '123456');


