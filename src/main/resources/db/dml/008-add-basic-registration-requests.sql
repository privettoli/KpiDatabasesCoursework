-- liquibase formatted SQL

-- changeset Roman_Metelyov:registration_request_1
INSERT INTO `registration_requests` (`unregistration_request_id`, `registration_permit_number`, `national_passport_series`, `national_passport_number`)
VALUES ((SELECT `id`
         FROM `unregistration_requests`
         WHERE `municipal_service_certificate_number` = 'THJN1'), 'THJN1', 'QQ', 852455);
-- rollback DELETE FROM `registration_requests` WHERE `registration_permit_number` = 'THJN1';

-- changeset Roman_Metelyov:registration_request_2
INSERT INTO `registration_requests` (`unregistration_request_id`, `registration_permit_number`, `national_passport_series`, `national_passport_number`)
VALUES ((SELECT `id`
         FROM `unregistration_requests`
         WHERE `municipal_service_certificate_number` = 'THJN2'), 'THJN2', 'QQ', 852456);
-- rollback DELETE FROM `registration_requests` WHERE `registration_permit_number` = 'THJN2';

-- changeset Roman_Metelyov:registration_request_3
INSERT INTO `registration_requests` (`unregistration_request_id`, `registration_permit_number`, `national_passport_series`, `national_passport_number`)
VALUES ((SELECT `id`
         FROM `unregistration_requests`
         WHERE `municipal_service_certificate_number` = 'THJN3'), 'THJN3', 'QQ', 852457);
-- rollback DELETE FROM `registration_requests` WHERE `registration_permit_number` = 'THJN3';

-- changeset Roman_Metelyov:registration_request_4
INSERT INTO `registration_requests` (`unregistration_request_id`, `registration_permit_number`, `national_passport_series`, `national_passport_number`)
VALUES ((SELECT `id`
         FROM `unregistration_requests`
         WHERE `municipal_service_certificate_number` = 'THJN4'), 'THJN4', 'QQ', 852458);
-- rollback DELETE FROM `registration_requests` WHERE `registration_permit_number` = 'THJN4';