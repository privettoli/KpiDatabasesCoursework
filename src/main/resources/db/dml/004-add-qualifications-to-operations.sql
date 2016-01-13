-- liquibase formatted SQL

-- changeset Anatolii_Papenko:qualification_to_operation_1
INSERT INTO `worker_qualifications_tech_operations` (`worker_qualification_id`, `tech_operation_id`)
VALUES ((SELECT `id`
         FROM `worker_qualifications`
         WHERE `qualification_name` = 'Farm Equipment Mechanic'),
        (SELECT `id`
         FROM `tech_operations`
         WHERE `name` = 'Sowing' AND `plant_id` = (SELECT `id`
                                                   FROM `plants`
                                                   WHERE `name` = 'Common wheat')));
-- rollback DELETE FROM `worker_qualifications_tech_operations` WHERE `tech_operation_id` = (SELECT `id` FROM `tech_operations` WHERE `name` = 'Sowing' AND `plant_id` = (SELECT `id` FROM `plants` WHERE `name` = 'Common wheat')) AND `worker_qualification_id` = (SELECT `id` FROM `worker_qualifications` WHERE `qualification_name` = 'Farm Equipment Mechanic');

-- changeset Anatolii_Papenko:qualification_to_operation_2
INSERT INTO `worker_qualifications_tech_operations` (`worker_qualification_id`, `tech_operation_id`)
VALUES ((SELECT `id`
         FROM `worker_qualifications`
         WHERE `qualification_name` = 'Gardener'),
        (SELECT `id`
         FROM `tech_operations`
         WHERE `name` = 'Sowing' AND `plant_id` = (SELECT `id`
                                                   FROM `plants`
                                                   WHERE `name` = 'Common wheat')));
-- rollback DELETE FROM `worker_qualifications_tech_operations` WHERE `tech_operation_id` = (SELECT `id` FROM `tech_operations` WHERE `name` = 'Sowing' AND `plant_id` = (SELECT `id` FROM `plants` WHERE `name` = 'Common wheat')) AND `worker_qualification_id` = (SELECT `id` FROM `worker_qualifications` WHERE `qualification_name` = 'Gardener');

-- changeset Anatolii_Papenko:qualification_to_operation_3
INSERT INTO `worker_qualifications_tech_operations` (`worker_qualification_id`, `tech_operation_id`)
VALUES ((SELECT `id`
         FROM `worker_qualifications`
         WHERE `qualification_name` = 'Pesticide Technician'),
        (SELECT `id`
         FROM `tech_operations`
         WHERE `name` = 'Pesticide poisoning' AND `plant_id` = (SELECT `id`
                                                                FROM `plants`
                                                                WHERE `name` = 'Common wheat')));
-- rollback DELETE FROM `worker_qualifications_tech_operations` WHERE `tech_operation_id` = (SELECT `id` FROM `tech_operations` WHERE `name` = 'Pesticide poisoning' AND `plant_id` = (SELECT `id` FROM `plants` WHERE `name` = 'Common wheat')) AND `worker_qualification_id` = (SELECT `id` FROM `worker_qualifications` WHERE `qualification_name` = 'Pesticide Technician');

-- changeset Anatolii_Papenko:qualification_to_operation_4
INSERT INTO `worker_qualifications_tech_operations` (`worker_qualification_id`, `tech_operation_id`)
VALUES ((SELECT `id`
         FROM `worker_qualifications`
         WHERE `qualification_name` = 'Agricultural Inspector'),
        (SELECT `id`
         FROM `tech_operations`
         WHERE `name` = 'Pesticide poisoning' AND `plant_id` = (SELECT `id`
                                                                FROM `plants`
                                                                WHERE `name` = 'Common wheat')));
-- rollback DELETE FROM `worker_qualifications_tech_operations` WHERE `tech_operation_id` = (SELECT `id` FROM `tech_operations` WHERE `name` = 'Pesticide poisoning' AND `plant_id` = (SELECT `id` FROM `plants` WHERE `name` = 'Common wheat')) AND `worker_qualification_id` = (SELECT `id` FROM `worker_qualifications` WHERE `qualification_name` = 'Agricultural Inspector');

-- changeset Anatolii_Papenko:qualification_to_operation_5
INSERT INTO `worker_qualifications_tech_operations` (`worker_qualification_id`, `tech_operation_id`)
VALUES ((SELECT `id`
         FROM `worker_qualifications`
         WHERE `qualification_name` = 'Gardener'),
        (SELECT `id`
         FROM `tech_operations`
         WHERE `name` = 'Harvesting' AND `plant_id` = (SELECT `id`
                                                       FROM `plants`
                                                       WHERE `name` = 'Common wheat')));
-- rollback DELETE FROM `worker_qualifications_tech_operations` WHERE `tech_operation_id` = (SELECT `id` FROM `tech_operations` WHERE `name` = 'Harvesting' AND `plant_id` = (SELECT `id` FROM `plants` WHERE `name` = 'Common wheat')) AND `worker_qualification_id` = (SELECT `id` FROM `worker_qualifications` WHERE `qualification_name` = 'Gardener');

-- changeset Anatolii_Papenko:qualification_to_operation_6
INSERT INTO `worker_qualifications_tech_operations` (`worker_qualification_id`, `tech_operation_id`)
VALUES ((SELECT `id`
         FROM `worker_qualifications`
         WHERE `qualification_name` = 'Agricultural Manager'),
        (SELECT `id`
         FROM `tech_operations`
         WHERE `name` = 'Harvesting' AND `plant_id` = (SELECT `id`
                                                       FROM `plants`
                                                       WHERE `name` = 'Common wheat')));
-- rollback DELETE FROM `worker_qualifications_tech_operations` WHERE `tech_operation_id` = (SELECT `id` FROM `tech_operations` WHERE `name` = 'Harvesting' AND `plant_id` = (SELECT `id` FROM `plants` WHERE `name` = 'Common wheat')) AND `worker_qualification_id` = (SELECT `id` FROM `worker_qualifications` WHERE `qualification_name` = 'Agricultural Manager');
