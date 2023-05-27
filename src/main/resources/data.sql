INSERT INTO `users` (
    `name`,
    `surname`,
    `dni_number`,
    `mail`,
    `password`,
    `phone`,
    `id_role`,
    `birthdate`
  )
VALUES
  (
    'Name',
    'Surname',
    '123',
    'email@some.com',
    '$2a$10$GlsGSNhkbVon6ZOSNMptOu5RikedRzlCAhMa7YpwvUSS0c88WT99S',
    '1234567890',
    1,
    '1992-05-11'
  );


INSERT INTO `roles` (`id`, `description`, `name`) VALUES ('1', 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO `roles` (`id`, `description`, `name`) VALUES ('2', 'ROLE_OWNER', 'ROLE_OWNER');
INSERT INTO `roles` (`id`, `description`, `name`) VALUES ('3', 'ROLE_EMPLOYEE', 'ROLE_EMPLOYEE');
INSERT INTO `roles` (`id`, `description`, `name`) VALUES ('4', 'ROLE_CLIENT', 'ROLE_CLIENT');

