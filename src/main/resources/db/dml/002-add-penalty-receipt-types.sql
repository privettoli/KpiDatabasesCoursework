-- liquibase formatted SQL

-- changeset Roman_Metelyov:penalty_receipt_type_late_obtaining
INSERT INTO `penalty_receipt_types` (`name`) VALUES ('Penalty because of late obtaining');
-- rollback DELETE FROM `penalty_receipt_types` WHERE `name` = 'Penalty because of late obtaining';

-- changeset Roman_Metelyov:penalty_receipt_type_loss
INSERT INTO `penalty_receipt_types` (`name`) VALUES ('Penalty because of loss');
-- rollback DELETE FROM `penalty_receipt_types` WHERE `name` = 'Penalty because of loss';