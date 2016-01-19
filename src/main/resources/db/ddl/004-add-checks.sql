-- liquibase formatted SQL

-- changeset Roman_Metelyov:late_passport_obtaining_chk
ALTER TABLE `national_passport_requests`
ADD CONSTRAINT `late_passport_obtaining_chk` CHECK (`birth_certificate_id` IS NOT NULL AND
                                                    ((EXTRACT(
                                                        YEAR FROM FROM_DAYS(DATEDIFF(CURDATE(), (
                                                          SELECT `birth_date`
                                                          FROM `birth_certificates`
                                                          WHERE `id` = `birth_certificate_id`)))
                                                    )) <= 18 OR `pernalty_receipt_id` IS NOT NULL));
-- rollback DROP CONSTRAINT `late_passport_obtaining_chk`;