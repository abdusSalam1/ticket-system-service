TRUNCATE `delivery_detail`;
    INSERT INTO `delivery_detail` (`delivery_id`, `current_distance_from_destination_in_meters`, `customer_type`, `delivery_status`, `expected_delivery_time`, `mean_time_to_prepare_food_in_minutes`, `rider_rating`, `time_to_reach_destination`) VALUES
(1,	200,	'VIP',	'Order received',	'2021-12-12 20:26:14',	20,	3,	'2021-12-12 20:06:15'),
(2,	200,	'NEW',	'Order received',	'2021-12-12 20:25:34',	20,	4,	'2021-12-12 20:06:34');

TRUNCATE `role`;
INSERT INTO `role` (`id`, `name`) VALUES
(1,	'ADMIN'),
(2,	'USER');

TRUNCATE `user`;
INSERT INTO `user` (`id`, `email`, `password`) VALUES
(1,	'admin@mail.com',	'$2a$10$Oyg5SYGlB.k6teZkoBAmLeZq.YtnDjSKss.rPNycygIzieXCVZhpa'),
(2,	'user@mail.com',	'$2a$10$Oyg5SYGlB.k6teZkoBAmLeZq.YtnDjSKss.rPNycygIzieXCVZhpa');

TRUNCATE `user_role`;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1,	1),
(2,	2);