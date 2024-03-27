CREATE TABLE `{prefix}players` (
  `uuid` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`uuid`)
);

CREATE TABLE `{prefix}users` (
  `id` VARCHAR(18),
   PRIMARY KEY (`id`),
   CONSTRAINT FK_UUID FOREIGN KEY (id)
   REFERENCES `{prefix}players` (`uuid`)
);