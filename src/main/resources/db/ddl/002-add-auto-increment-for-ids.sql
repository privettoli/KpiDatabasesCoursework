-- liquibase formatted SQL

-- changeset Anatolii_Papenko:plants_auto_increment
ALTER TABLE `plants`
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT;
-- rollback ALTER TABLE `plants` CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL ;

-- changeset Anatolii_Papenko:worker_qualifications_auto_increment
ALTER TABLE `worker_qualifications`
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT;
-- rollback ALTER TABLE `worker_qualifications` CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL ;

-- changeset Anatolii_Papenko:tech_operations_auto_increment
ALTER TABLE `tech_operations`
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT;
-- rollback ALTER TABLE `tech_operations` CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL ;
