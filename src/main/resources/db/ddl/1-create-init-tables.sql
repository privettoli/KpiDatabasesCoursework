-- liquibase formatted SQL

-- changeset Anatolii_Papenko:plants
CREATE TABLE `plants` (
  `id`         INT UNSIGNED                NOT NULL,
  `name`       VARCHAR(255) UNIQUE         NOT NULL,
  `area_ares` DECIMAL(7, 2) UNSIGNED      NOT NULL,
  PRIMARY KEY (`id`)
);
-- ROLLBACK DROP TABLE `plants`;

-- changeset Anatolii_Papenko:tech_operations
CREATE TABLE `tech_operations` (
  `id`                      INT UNSIGNED           NOT NULL,
  `month`                   TINYINT UNSIGNED       NOT NULL,
  `name`                    VARCHAR(255)           NOT NULL,
  `fuel_consumption_liters` DECIMAL(7, 2) UNSIGNED NOT NULL,
  `processing_time_weeks`   DECIMAL(7, 2) UNSIGNED NOT NULL,
  `plant_id`                INT UNSIGNED           NOT NULL,
  PRIMARY KEY (`id`)
);
-- ROLLBACK DROP TABLE `tech_operations`;

-- changeset Anatolii_Papenko:worker_qualifications
CREATE TABLE `worker_qualifications` (
  `id`                 INT UNSIGNED NOT NULL,
  `qualification_name` VARCHAR(255) NOT NULL,
  `salary_by_hour_uah` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
);
-- ROLLBACK DROP TABLE `worker_qualifications`;

-- changeset Anatolii_Papenko:worker_qualifications_tech_operations
CREATE TABLE `worker_qualifications_tech_operations` (
  `worker_qualification_id` INT UNSIGNED NOT NULL,
  `tech_operation_id`       INT UNSIGNED NOT NULL
);
-- ROLLBACK DROP TABLE `worker_qualifications_tech_operations`;