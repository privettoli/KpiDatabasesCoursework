-- liquibase formatted SQL

-- changeset Roman_Metelyov:unregistration_request_1
INSERT INTO `unregistration_requests` (`municipal_service_certificate_number`, `national_passport_series`, `national_passport_number`)
VALUES ('THJN1', 'QQ', 852455);
-- rollback DELETE FROM `unregistration_requests` WHERE `municipal_service_certificate_number` = 'THJN1';

-- changeset Roman_Metelyov:unregistration_request_2
INSERT INTO `unregistration_requests` (`municipal_service_certificate_number`, `national_passport_series`, `national_passport_number`)
VALUES ('THJN2', 'QQ', 852456);
-- rollback DELETE FROM `unregistration_requests` WHERE `municipal_service_certificate_number` = 'THJN2';

-- changeset Roman_Metelyov:unregistration_request_3
INSERT INTO `unregistration_requests` (`municipal_service_certificate_number`, `national_passport_series`, `national_passport_number`)
VALUES ('THJN3', 'QQ', 852457);
-- rollback DELETE FROM `unregistration_requests` WHERE `municipal_service_certificate_number` = 'THJN3';

-- changeset Roman_Metelyov:unregistration_request_4
INSERT INTO `unregistration_requests` (`municipal_service_certificate_number`, `national_passport_series`, `national_passport_number`)
VALUES ('THJN4', 'QQ', 852458);
-- rollback DELETE FROM `unregistration_requests` WHERE `municipal_service_certificate_number` = 'THJN4';
