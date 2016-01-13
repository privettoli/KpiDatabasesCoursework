-- liquibase formatted SQL

-- changeset Anatolii_Papenko:tech_operation_plant_fk
ALTER TABLE `tech_operations`
ADD CONSTRAINT `tech_operation_plant_fk`
FOREIGN KEY (`plant_id`)
REFERENCES `plants` (`id`)
  ON UPDATE NO ACTION
  ON DELETE RESTRICT;
-- rollback ALTER TABLE `tech_operations` DROP FOREIGN KEY `tech_operation_plant_fk`;

-- changeset Anatolii_Papenko:worker_qualifications_tech_operations_fks
ALTER TABLE `worker_qualifications_tech_operations`
ADD CONSTRAINT `workers_tech_operations_worker_qualification_fk`
FOREIGN KEY (`worker_qualification_id`)
REFERENCES `worker_qualifications` (`id`)
  ON UPDATE NO ACTION
  ON DELETE RESTRICT,
ADD CONSTRAINT `workers_tech_operations_tech_operation_fk`
FOREIGN KEY (`tech_operation_id`)
REFERENCES `tech_operations` (`id`)
  ON UPDATE NO ACTION
  ON DELETE RESTRICT;
-- rollback ALTER TABLE `worker_qualifications_tech_operations` DROP FOREIGN KEY `workers_tech_operations_worker_qualification_fk`, DROP FOREIGN KEY `workers_tech_operations_tech_operation_fk`;