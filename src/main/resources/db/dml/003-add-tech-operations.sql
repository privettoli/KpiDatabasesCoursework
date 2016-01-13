-- liquibase formatted SQL

-- changeset Anatolii_Papenko:tech_operation_1_sowing_common_wheat
INSERT INTO `tech_operations` (`name`, `month`, `fuel_consumption_liters`, `processing_time_weeks`, `plant_id`)
VALUES ('Sowing', 1, 250, 2, (SELECT `id`
                              FROM `plants`
                              WHERE `name` = 'Common wheat'));
-- rollback DELETE FROM `tech_operations` WHERE `name` = 'Sowing' AND `plant_id` = (SELECT `id` FROM `plants` WHERE `name` = 'Common wheat');

-- changeset Anatolii_Papenko:tech_operation_2_pesticide_poisoning_common_wheat
INSERT INTO `tech_operations` (`name`, `month`, `fuel_consumption_liters`, `processing_time_weeks`, `plant_id`)
VALUES ('Pesticide poisoning', 1, 50, 1, (SELECT `id`
                                          FROM `plants`
                                          WHERE `name` = 'Common wheat'));
-- rollback DELETE FROM `tech_operations` WHERE `name` = 'Pesticide poisoning' AND `plant_id` = (SELECT `id` FROM `plants` WHERE `name` = 'Common wheat');

-- changeset Anatolii_Papenko:tech_operation_3_harvesting
INSERT INTO `tech_operations` (`name`, `month`, `fuel_consumption_liters`, `processing_time_weeks`, `plant_id`)
VALUES ('Harvesting', 2, 250, 2, (SELECT `id`
                                          FROM `plants`
                                          WHERE `name` = 'Common wheat'));
-- rollback DELETE FROM `tech_operations` WHERE `name` = 'Harvesting' AND `plant_id` = (SELECT `id` FROM `plants` WHERE `name` = 'Common wheat');
