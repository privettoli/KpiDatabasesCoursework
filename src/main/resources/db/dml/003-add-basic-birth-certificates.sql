-- liquibase formatted SQL

-- changeset Roman_Metelyov:birth_certificate_1
INSERT INTO `birth_certificates` (`birth_certificate_series`, `birth_certificate_number`, `birth_date`)
VALUES ('AB', 11531, '1997-03-21');
-- rollback DELETE FROM `birth_certificates` WHERE `birth_certificate_series` = 'AB';

-- changeset Roman_Metelyov:birth_certificate_2
INSERT INTO `birth_certificates` (`birth_certificate_series`, `birth_certificate_number`, `birth_date`)
VALUES ('CD', 14452, '1993-03-04');
-- rollback DELETE FROM `birth_certificates` WHERE `birth_certificate_series` = 'CD';

-- changeset Roman_Metelyov:birth_certificate_3
INSERT INTO `birth_certificates` (`birth_certificate_series`, `birth_certificate_number`, `birth_date`)
VALUES ('EF', 51531, '2012-12-21');
-- rollback DELETE FROM `birth_certificates` WHERE `birth_certificate_series` = 'EF';

-- changeset Roman_Metelyov:birth_certificate_4
INSERT INTO `birth_certificates` (`birth_certificate_series`, `birth_certificate_number`, `birth_date`)
VALUES ('FE', 16515, '1991-05-06');
-- rollback DELETE FROM `birth_certificates` WHERE `birth_certificate_series` = 'FE';

-- changeset Roman_Metelyov:birth_certificate_5
INSERT INTO `birth_certificates` (`birth_certificate_series`, `birth_certificate_number`, `birth_date`)
VALUES ('DC', 69122, '2007-05-06');
-- rollback DELETE FROM `birth_certificates` WHERE `birth_certificate_series` = 'DC';