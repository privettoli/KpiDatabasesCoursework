-- liquibase formatted SQL

-- changeset Roman_Metelyov:late_passport_obtaining_chk
DELIMITER $$
CREATE TRIGGER `late_passport_obtaining_chk` BEFORE INSERT ON `national_passport_requests`
FOR EACH ROW
  BEGIN
    IF `birth_certificate_id` IS NULL OR
       ((EXTRACT(
           YEAR FROM FROM_DAYS(DATEDIFF(CURDATE(), (
             SELECT `birth_date`
             FROM `birth_certificates`
             WHERE `id` = `birth_certificate_id`)))
       )) > 18 AND `penalty_receipt_id` IS NULL)
    THEN
      SIGNAL SQLSTATE '23001'
      SET MESSAGE_TEXT = 'Late passport obtaining, you should pay your penalty';
    END IF;
  END$$
DELIMITER ;
-- rollback DROP TRIGGER IF EXISTS `late_passport_obtaining_chk`;