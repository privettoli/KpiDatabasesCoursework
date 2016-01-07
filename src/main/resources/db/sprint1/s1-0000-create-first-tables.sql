-- liquibase formatted SQL

-- changeset Anatolii_Papenko:PROJECT-0000
CREATE TABLE `technological_map` (
  `id`                      INT UNSIGNED           NOT NULL,
  `month`                   TINYINT UNSIGNED       NOT NULL,
  `name`                    VARCHAR(512)           NOT NULL,
  `fuel_consumption_liters` DECIMAL(7, 2) UNSIGNED NOT NULL,
  `processing_time_weeks`   DECIMAL(7, 2) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
);
-- ROLLBACK DROP TABLE `technological_map`;