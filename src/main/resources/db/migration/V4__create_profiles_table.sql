CREATE TABLE IF NOT EXISTS `profiles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bio` varchar(500),
  `phone_number` varchar(255),
  `date_of_birth` date,
  `loyalty_points` int unsigned default 0,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_unique` (`user_id`),
  CONSTRAINT `profiles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;