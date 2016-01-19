-- liquibase formatted SQL

-- changeset Roman_Metelyov:national_passport_request_1
INSERT INTO `national_passport_requests` (`municipal_service_certificate_number`, `birth_certificate_id`, `photo`, `issue_reason_id`)
VALUES ('AB4235', (SELECT `id`
                   FROM `birth_certificates`
                   WHERE `birth_certificate_series` = 'AB'), 1, (SELECT `id`
                                                                 FROM `issue_reasons`
                                                                 WHERE `name` = 'First-time issue');
-- rollback DELETE FROM `national_passport_requests` WHERE `municipal_service_certificate_number` = 'AB4235' AND `birth_certificate_id` = (SELECT `id` FROM `birth_certificates` WHERE `birth_certificate_series` = 'AB'));

-- changeset Roman_Metelyov:national_passport_request_2
INSERT INTO `national_passport_requests` (`municipal_service_certificate_number`, `birth_certificate_id`, `photo`, `issue_reason_id`, `penalty_receipt_id`)
VALUES ('AB4235', (SELECT `id`
                   FROM `birth_certificates`
                   WHERE `birth_certificate_series` = 'AB'), 1, (SELECT `id`
                                                                 FROM `issue_reasons`
                                                                 WHERE `name` = 'Issue because of loss'), (SELECT `id`
                                                                                                          FROM `penalty_receipts`
                                                                                                          WHERE `name` = 'Penalty because of loss');
-- rollback DELETE FROM `national_passport_requests` WHERE `municipal_service_certificate_number` = 'AB4235' AND `penalty_receipt_id` = (SELECT `id` FROM `penalty_receipts` WHERE `name` = 'Penalty because of loss'));

-- changeset Roman_Metelyov:national_passport_request_3
INSERT INTO `national_passport_requests` (`municipal_service_certificate_number`, `birth_certificate_id`, `photo`, `issue_reason_id`)
VALUES ('AB4236', (SELECT `id`
                   FROM `birth_certificates`
                   WHERE `birth_certificate_series` = 'CD'), 1, (SELECT `id`
                                                                 FROM `issue_reasons`
                                                                 WHERE `name` = 'First-time issue');
-- rollback DELETE FROM `national_passport_requests` WHERE `municipal_service_certificate_number` = 'AB4236' AND `birth_certificate_id` = (SELECT `id` FROM `birth_certificates` WHERE `birth_certificate_series` = 'CD'));

-- changeset Roman_Metelyov:national_passport_request_4
INSERT INTO `national_passport_requests` (`municipal_service_certificate_number`, `birth_certificate_id`, `photo`, `issue_reason_id`)
VALUES ('AB4237', (SELECT `id`
                   FROM `birth_certificates`
                   WHERE `birth_certificate_series` = 'EF'), 1, (SELECT `id`
                                                                 FROM `issue_reasons`
                                                                 WHERE `name` = 'First-time issue');
-- rollback DELETE FROM `national_passport_requests` WHERE `municipal_service_certificate_number` = 'AB4237' AND `birth_certificate_id` = (SELECT `id` FROM `birth_certificates` WHERE `birth_certificate_series` = 'EF'));
