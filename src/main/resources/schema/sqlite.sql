CREATE TABLE `{prefix}players` (
  `uuid` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`uuid`)
);

CREATE TABLE `{prefix}links` (
  `player_id` VARCHAR(36) NOT NULL,
  `long` VARCHAR(18),
   PRIMARY KEY (`player_id`, `long`),
   CONSTRAINT FK_UUID FOREIGN KEY (player_id)
   REFERENCES `{prefix}players` (`uuid`)
);