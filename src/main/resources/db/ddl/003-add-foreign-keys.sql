-- liquibase formatted SQL

-- changeset Roman_Metelyov:national_passport_request_penalty_receipt_fk
ALTER TABLE `national_passport_requests`
ADD CONSTRAINT `national_passport_request_penalty_receipt_fk`
FOREIGN KEY (`penalty_receipts_id`)
REFERENCES `penalty_receipts` (`id`)
  ON UPDATE NO ACTION
  ON DELETE RESTRICT,
ADD CONSTRAINT `national_passport_requests_issue_reason_fk`
FOREIGN KEY (`issue_reason_id`)
REFERENCES `issue_reasons` (`id`)
  ON UPDATE NO ACTION
  ON DELETE RESTRICT;
-- rollback ALTER TABLE `national_passport_requests` DROP FOREIGN KEY `national_passport_request_penalty_receipt_fk`, DROP FOREIGN KEY `national_passport_requests_issue_reason_fk`;

-- changeset Roman_Metelyov:registration_request_unregistration_request_fk
ALTER TABLE `registration_requests`
ADD CONSTRAINT `registration_request_unregistration_request_fk`
FOREIGN KEY (`unregistration_request_id`)
REFERENCES `unregistration_requests` (`id`)
  ON UPDATE NO ACTION
  ON DELETE RESTRICT;
-- rollback ALTER TABLE `registration_requests` DROP FOREIGN KEY `registration_request_unregistration_request_fk`;

-- changeset Roman_Metelyov:national_passport_request_birth_certificate_fk
ALTER TABLE `national_passport_requests`
ADD CONSTRAINT `national_passport_request_birth_certificate_fk`
FOREIGN KEY (`birth_certificate_id`)
REFERENCES `birth_certificates` (`id`)
  ON UPDATE NO ACTION
  ON DELETE RESTRICT;
-- rollback ALTER TABLE `national_passport_requests` DROP FOREIGN KEY `national_passport_request_birth_certificate_fk`;
