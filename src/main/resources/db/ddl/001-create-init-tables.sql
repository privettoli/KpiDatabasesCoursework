-- liquibase formatted SQL

-- changeset Roman_Metelyov:national_passport_requests
CREATE TABLE `national_passport_requests` (
  `id`                                   INT UNSIGNED                NOT NULL,
  `municipal_service_certificate_number` VARCHAR(255) UNIQUE         NOT NULL,
  `birth_certificate_id`                 INT UNSIGNED                NOT NULL,
  `photo`                                BOOLEAN                     NOT NULL,
  `issue_reason_id`                      INT UNSIGNED                NOT NULL,
  `penalty_receipt_id`                   INT UNSIGNED,
  `police_confirmation`                  BOOLEAN,
  PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `national_passport_requests`;

-- changeset Roman_Metelyov:penalty_receipts
CREATE TABLE `penalty_receipts` (
  `id`   INT UNSIGNED               NOT NULL,
  `name` VARCHAR(255) UNIQUE        NOT NULL,
  `tax`  DECIMAL(5, 2) UNSIGNED     NOT NULL,
  `penalty_receipt_sum` DECIMAL(5, 2) UNSIGNED     NOT NULL,
  PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `penalty_receipts`;

-- changeset Roman_Metelyov:issue_reasons
CREATE TABLE `issue_reasons` (
  `id`   INT UNSIGNED                NOT NULL,
  `name` VARCHAR(255) UNIQUE         NOT NULL,
  PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `issue_reasons`;

-- changeset Roman_Metelyov:foreign_passport_requests
CREATE TABLE `foreign_passport_requests` (
  `id`                                    INT UNSIGNED                NOT NULL,
  `national_passport_series`              VARCHAR(2)          NOT NULL,
  `national_passport_number`              INT UNSIGNED                NOT NULL,
  `conviction_absence_certificate_number` VARCHAR(255) UNIQUE         NOT NULL,
  `military_certificate_number`           VARCHAR(255) UNIQUE         NOT NULL,
  PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `foreign_passport_requests`;

-- changeset Roman_Metelyov:registration_requests
CREATE TABLE `registration_requests` (
  `id`                         INT UNSIGNED                NOT NULL,
  `unregistration_request_id`  INT UNSIGNED                NOT NULL,
  `registration_permit_number` VARCHAR(255) UNIQUE         NOT NULL,
  `national_passport_series`   VARCHAR(2)           NOT NULL,
  `national_passport_number`   INT UNSIGNED                NOT NULL,
  PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `registration_requests`;

-- changeset Roman_Metelyov:unregistration_requests
CREATE TABLE `unregistration_requests` (
  `id`                                   INT UNSIGNED                NOT NULL,
  `municipal_service_certificate_number` VARCHAR(255) UNIQUE         NOT NULL,
  `national_passport_series`             VARCHAR(2)          NOT NULL,
  `national_passport_number`             INT UNSIGNED                NOT NULL,
  PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `unregistration_requests`;

-- changeset Roman_Metelyov:birth_certificates
CREATE TABLE `birth_certificates` (
  `id`                       INT UNSIGNED                NOT NULL,
  `birth_certificate_series` VARCHAR(2)           NOT NULL,
  `birth_certificate_number` INT UNSIGNED                NOT NULL,
  `birth_date`               DATE                        NOT NULL,
  PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `birth_certificates`;