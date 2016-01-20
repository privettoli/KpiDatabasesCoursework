-- liquibase formatted SQL

-- changeset Roman_Metelyov:penalty_receipt_1
INSERT INTO `penalty_receipts` (`type_id`, `tax`, `penalty_receipt_sum`) VALUES ((SELECT `id`
                                                                                  FROM `penalty_receipt_types`
                                                                                  WHERE `name` =
                                                                                        'Penalty because of late obtaining'),
                                                                                 0.05, 250.05);
-- rollback DELETE FROM `penalty_receipts` WHERE `type_id` = (SELECT `id` FROM `penalty_receipt_types` WHERE `name` = 'Penalty because of late obtaining') AND `tax` = 0.05 AND `penalty_receipt_sum` = 250.05;

-- changeset Roman_Metelyov:penalty_receipt_2
INSERT INTO `penalty_receipts` (`type_id`, `tax`, `penalty_receipt_sum`)
VALUES ((SELECT `id`
         FROM `penalty_receipt_types`
         WHERE `name` = 'Penalty because of loss'), 0.15, 949.99);
-- rollback DELETE FROM `penalty_receipts` WHERE `type_id` = (SELECT `id` FROM `penalty_receipt_types` WHERE `name` = 'Penalty because of loss') AND `tax` = 0.15 AND `penalty_receipt_sum` = 949.99;
