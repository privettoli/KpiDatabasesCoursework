-- liquibase formatted SQL

-- changeset Roman_Metelyov:foreign_passport_request_1
INSERT INTO `foreign_passport_requests` (`national_passport_series`, `national_passport_number`, `conviction_absence_certificate_number`, `military_certificate_number`)
VALUES ('QQ', 852455, 'ER197326G', 'TGI2588');
-- rollback DELETE FROM `foreign_passport_requests` WHERE `military_certificate_number` = 'TGI2588' AND `conviction_absence_certificate_number` = 'ER197326G');

-- changeset Roman_Metelyov:foreign_passport_request_2
INSERT INTO `foreign_passport_requests` (`national_passport_series`, `national_passport_number`, `conviction_absence_certificate_number`, `military_certificate_number`)
VALUES ('QQ', 852456, 'ER197326H', 'TGI2589');
-- rollback DELETE FROM `foreign_passport_requests` WHERE `military_certificate_number` = 'TGI2589' AND `conviction_absence_certificate_number` = 'ER197326H');

-- changeset Roman_Metelyov:foreign_passport_request_3
INSERT INTO `foreign_passport_requests` (`national_passport_series`, `national_passport_number`, `conviction_absence_certificate_number`, `military_certificate_number`)
VALUES ('QQ', 852457, 'ER197326I', 'TGI2590');
-- rollback DELETE FROM `foreign_passport_requests` WHERE `military_certificate_number` = 'TGI2590' AND `conviction_absence_certificate_number` = 'ER197326I');

-- changeset Roman_Metelyov:foreign_passport_request_4
INSERT INTO `foreign_passport_requests` (`national_passport_series`, `national_passport_number`, `conviction_absence_certificate_number`, `military_certificate_number`)
VALUES ('QQ', 852458, 'ER197326K', 'TGI2591');
-- rollback DELETE FROM `foreign_passport_requests` WHERE `military_certificate_number` = 'TGI2591' AND `conviction_absence_certificate_number` = 'ER197326K');