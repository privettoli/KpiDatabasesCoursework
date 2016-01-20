-- liquibase formatted SQL

-- changeset Roman_Metelyov:national_passport_requests_auto_increment
ALTER TABLE `national_passport_requests`
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT;
-- rollback ALTER TABLE `national_passport_requests` CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL ;

-- changeset Roman_Metelyov:penalty_receipts_auto_increment
ALTER TABLE `penalty_receipts`
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT;
-- rollback ALTER TABLE `penalty_receipts` CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL ;

-- changeset Roman_Metelyov:issue_reasons_auto_increment
ALTER TABLE `issue_reasons`
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT;
-- rollback ALTER TABLE `issue_reasons` CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL ;

-- changeset Roman_Metelyov:foreign_passport_requests_auto_increment
ALTER TABLE `foreign_passport_requests`
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT;
-- rollback ALTER TABLE `foreign_passport_requests` CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL ;

-- changeset Roman_Metelyov:registration_requests_auto_increment
ALTER TABLE `registration_requests`
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT;
-- rollback ALTER TABLE `registration_requests` CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL ;

-- changeset Roman_Metelyov:unregistration_requests_auto_increment
ALTER TABLE `unregistration_requests`
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT;
-- rollback ALTER TABLE `unregistration_requests` CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL ;

-- changeset Roman_Metelyov:birth_certificates_auto_increment
ALTER TABLE `birth_certificates`
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT;
-- rollback ALTER TABLE `birth_certificates` CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL ;

-- changeset Roman_Metelyov:penalty_receipt_types_auto_increment
ALTER TABLE `penalty_receipt_types`
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT;
-- rollback ALTER TABLE `penalty_receipt_types` CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL ;