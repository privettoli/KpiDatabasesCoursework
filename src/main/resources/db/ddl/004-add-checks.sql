-- liquibase formatted SQL

-- changeset Roman_Metelyov:late_passport_obtaining_chk endDelimiter:\nGO 
CREATE TRIGGER `late_passport_obtaining_chk` BEFORE INSERT ON `national_passport_requests`
FOR EACH ROW BEGIN
  IF NEW.`birth_certificate_id` IS NULL OR
     ((EXTRACT(
         YEAR FROM FROM_DAYS(DATEDIFF(CURDATE(), (
           SELECT `birth_certificates`.`birth_date`
           FROM `birth_certificates`
           WHERE `birth_certificates`.`id` = NEW.`birth_certificate_id`)))
     )) > 18 AND NEW.`penalty_receipt_id` IS NULL)
  THEN
    SIGNAL SQLSTATE '23001'
    SET MESSAGE_TEXT = 'Late passport obtaining, you should pay your penalty';
  END IF;
END
GO
-- rollback DROP TRIGGER IF EXISTS `late_passport_obtaining_chk`;