-- healthrecord.diagnosis definition

CREATE TABLE IF NOT EXISTS `diagnosis` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `visit_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_diagnosis_code` (`code`),
  KEY `idx_diagnosis_description` (`description`),
  KEY `idx_diagnosis_visit_id` (`visit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- healthrecord.doctor definition

CREATE TABLE IF NOT EXISTS `doctor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `specialty` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_doctor_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- healthrecord.patient definition

CREATE TABLE IF NOT EXISTS `patient` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dob` date NOT NULL,
  `gender` enum('FEMALE','MALE','OTHER') NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_patient_name` (`name`),
  KEY `idx_patient_gender` (`gender`),
  KEY `idx_patient_dob` (`dob`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- healthrecord.patient_cohort_selection_set definition

CREATE TABLE IF NOT EXISTS `patient_cohort_selection_set` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `query_criteria` json NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_cohort_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- healthrecord.visit definition

CREATE TABLE IF NOT EXISTS `visit` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `doctor_id` bigint NOT NULL,
  `patient_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_visit_patient_id` (`patient_id`),
  KEY `idx_visit_doctor_id` (`doctor_id`),
  KEY `idx_visit_date` (`date`),
  KEY `idx_visit_patient_date` (`patient_id`,`date`),
  CONSTRAINT `FKc63541y8ppkvsovm00gumv90t` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`),
  CONSTRAINT `FKrban5yeabnx30seqm69jw44e` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;