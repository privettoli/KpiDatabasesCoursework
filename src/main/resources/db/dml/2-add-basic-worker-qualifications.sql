-- liquibase formatted SQL

-- changeset Anatolii_Papenko:basic_worker_qualification_agricultural_inspector
INSERT INTO `worker_qualifications` (`qualification_name`, `salary_by_hour_uah`)
VALUES ('Agricultural Inspector', '100');
-- ROLLBACK DELETE FROM `worker_qualifications` WHERE `name` = 'Agricultural Inspector';

-- changeset Anatolii_Papenko:basic_worker_qualification_agricultural_manager
INSERT INTO `worker_qualifications` (`qualification_name`, `salary_by_hour_uah`)
VALUES ('Agricultural Manager', '150');
-- ROLLBACK DELETE FROM `worker_qualifications` WHERE `name` = 'Agricultural Manager';

-- changeset Anatolii_Papenko:basic_worker_qualification_farm_equipment_mechanic
INSERT INTO `worker_qualifications` (`qualification_name`, `salary_by_hour_uah`)
VALUES ('Farm Equipment Mechanic', '125');
-- ROLLBACK DELETE FROM `worker_qualifications` WHERE `name` = 'Farm Equipment Mechanic';

-- changeset Anatolii_Papenko:basic_worker_qualification_gardener
INSERT INTO `worker_qualifications` (`qualification_name`, `salary_by_hour_uah`)
VALUES ('Gardener', '60');
-- ROLLBACK DELETE FROM `worker_qualifications` WHERE `name` = 'Gardener';

-- changeset Anatolii_Papenko:basic_worker_qualification_pesticide_technician
INSERT INTO `worker_qualifications` (`qualification_name`, `salary_by_hour_uah`)
VALUES ('Pesticide Technician', '80');
-- ROLLBACK DELETE FROM `worker_qualifications` WHERE `name` = 'Pesticide Technician';