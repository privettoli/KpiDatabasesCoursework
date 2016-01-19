-- liquibase formatted SQL

-- changeset Roman_Metelyov:penalty_receipt_1_penalty_because_of_delay
INSERT INTO `penalty_receipts` (`name`, `tax`, `penalty_receipt_sum`) VALUES ('Penalty because of delay', 0.05, 250.05);
-- rollback DELETE FROM `penalty_receipts` WHERE `name` = 'Penalty because of delay';

-- changeset Roman_Metelyov:penalty_receipt_2_penalty_because_of_loss
INSERT INTO `penalty_receipts` (`name`, `tax`, `penalty_receipt_sum`) VALUES ('Penalty because of loss', 0.15, 949.99);
-- rollback DELETE FROM `penalty_receipts` WHERE `name` = 'Penalty because of loss';