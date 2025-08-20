-- Insert test data for Doctor
INSERT INTO doctor (id, name, specialty) VALUES
(1, 'Dr Alice Smith', 'Cardiology'),
(2, 'Dr Bob Johnson', 'Neurology'),
(3, 'Dr Carol Lee', 'Pediatrics');


-- Insert test data for Patient
INSERT INTO patient (id, name, dob, gender) VALUES
(1, 'John Doe', '1980-01-15', 'Male'),
(2, 'Jane Roe', '1975-05-20', 'Female'),
(3, 'Sam Green', '1990-09-10', 'Male'),
(4, 'Lisa Brown', '1985-12-05', 'Female'),
(5, 'Tom White', '2000-07-22', 'Male');

-- Insert test data for Visit (10 visits per patient)
INSERT INTO visit (id, patient_id, date, doctor_id) VALUES
-- Visits for John Doe
(1, 1, '2024-01-01 10:00:00', 1),
(2, 1, '2024-01-10 11:00:00', 2),
(3, 1, '2024-01-20 09:30:00', 3),
(4, 1, '2024-02-01 14:00:00', 1),
(5, 1, '2024-02-10 15:00:00', 2),
(6, 1, '2024-02-20 16:30:00', 3),
(7, 1, '2024-03-01 10:00:00', 1),
(8, 1, '2024-03-10 11:00:00', 2),
(9, 1, '2024-03-20 09:30:00', 3),
(10, 1, '2024-04-01 14:00:00', 1),
-- Visits for Jane Roe
(11, 2, '2024-01-02 10:00:00', 2),
(12, 2, '2024-01-12 11:00:00', 3),
(13, 2, '2024-01-22 09:30:00', 1),
(14, 2, '2024-02-02 14:00:00', 2),
(15, 2, '2024-02-12 15:00:00', 3),
(16, 2, '2024-02-22 16:30:00', 1),
(17, 2, '2024-03-02 10:00:00', 2),
(18, 2, '2024-03-12 11:00:00', 3),
(19, 2, '2024-03-22 09:30:00', 1),
(20, 2, '2024-04-02 14:00:00', 2),
-- Visits for Sam Green
(21, 3, '2024-01-03 10:00:00', 3),
(22, 3, '2024-01-13 11:00:00', 1),
(23, 3, '2024-01-23 09:30:00', 2),
(24, 3, '2024-02-03 14:00:00', 3),
(25, 3, '2024-02-13 15:00:00', 1),
(26, 3, '2024-02-23 16:30:00', 2),
(27, 3, '2024-03-03 10:00:00', 3),
(28, 3, '2024-03-13 11:00:00', 1),
(29, 3, '2024-03-23 09:30:00', 2),
(30, 3, '2024-04-03 14:00:00', 3),
-- Visits for Lisa Brown
(31, 4, '2024-01-04 10:00:00', 1),
(32, 4, '2024-01-14 11:00:00', 2),
(33, 4, '2024-01-24 09:30:00', 3),
(34, 4, '2024-02-04 14:00:00', 1),
(35, 4, '2024-02-14 15:00:00', 2),
(36, 4, '2024-02-24 16:30:00', 3),
(37, 4, '2024-03-04 10:00:00', 1),
(38, 4, '2024-03-14 11:00:00', 2),
(39, 4, '2024-03-24 09:30:00', 3),
(40, 4, '2024-04-04 14:00:00', 1),
-- Visits for Tom White
(41, 5, '2024-01-05 10:00:00', 2),
(42, 5, '2024-01-15 11:00:00', 3),
(43, 5, '2024-01-25 09:30:00', 1),
(44, 5, '2024-02-05 14:00:00', 2),
(45, 5, '2024-02-15 15:00:00', 3),
(46, 5, '2024-02-25 16:30:00', 1),
(47, 5, '2024-03-05 10:00:00', 2),
(48, 5, '2024-03-15 11:00:00', 3),
(49, 5, '2024-03-25 09:30:00', 1),
(50, 5, '2024-04-05 14:00:00', 2);

-- Insert test data for Diagnosis (one per visit)
INSERT INTO diagnosis (id, visit_id, code, description) VALUES
-- Diagnoses for visits 1-10
(1, 1, 'I10', 'Hypertension'),
(2, 2, 'E11', 'Type 2 Diabetes'),
(3, 3, 'J45', 'Asthma'),
(4, 4, 'I20', 'Angina'),
(5, 5, 'E78', 'High Cholesterol'),
(6, 6, 'J20', 'Bronchitis'),
(7, 7, 'I25', 'Coronary Artery Disease'),
(8, 8, 'E66', 'Obesity'),
(9, 9, 'J30', 'Allergic Rhinitis'),
(10, 10, 'I50', 'Heart Failure'),
-- Diagnoses for visits 11-20
(11, 11, 'G40', 'Epilepsy'),
(12, 12, 'F32', 'Depression'),
(13, 13, 'M54', 'Back Pain'),
(14, 14, 'N39', 'Urinary Infection'),
(15, 15, 'K21', 'GERD'),
(16, 16, 'L40', 'Psoriasis'),
(17, 17, 'M17', 'Knee Osteoarthritis'),
(18, 18, 'E03', 'Hypothyroidism'),
(19, 19, 'H52', 'Myopia'),
(20, 20, 'J06', 'Common Cold'),
-- Diagnoses for visits 21-30
(21, 21, 'A09', 'Diarrhea'),
(22, 22, 'B34', 'Viral Infection'),
(23, 23, 'C50', 'Breast Cancer'),
(24, 24, 'D64', 'Anemia'),
(25, 25, 'E10', 'Type 1 Diabetes'),
(26, 26, 'F41', 'Anxiety'),
(27, 27, 'G43', 'Migraine'),
(28, 28, 'H10', 'Conjunctivitis'),
(29, 29, 'I10', 'Hypertension'),
(30, 30, 'J45', 'Asthma'),
-- Diagnoses for visits 31-40
(31, 31, 'K52', 'Gastroenteritis'),
(32, 32, 'L20', 'Eczema'),
(33, 33, 'M51', 'Disc Disorder'),
(34, 34, 'N20', 'Kidney Stone'),
(35, 35, 'O21', 'Nausea'),
(36, 36, 'P07', 'Prematurity'),
(37, 37, 'Q21', 'Heart Defect'),
(38, 38, 'R51', 'Headache'),
(39, 39, 'S06', 'Concussion'),
(40, 40, 'T81', 'Complication'),
-- Diagnoses for visits 41-50
(41, 41, 'U07', 'COVID-19'),
(42, 42, 'V01', 'Accident'),
(43, 43, 'W19', 'Fall'),
(44, 44, 'X59', 'Exposure'),
(45, 45, 'Y83', 'Surgical Complication'),
(46, 46, 'Z00', 'General Exam'),
(47, 47, 'Z23', 'Immunization'),
(48, 48, 'Z51', 'Therapy'),
(49, 49, 'Z72', 'Lifestyle Risk'),
(50, 50, 'Z99', 'Dependence');

-- Insert test data for Patient Cohort Selection Set
INSERT INTO patient_cohort_selection_set (id, name, description, query_criteria) VALUES
(1, 'All Males', 'All male patients', '{"op": "eq", "field": "gender", "value": "male"}'),
(2, 'All Females', 'All female patients', '{"op": "eq", "field": "gender", "value": "female"}'),
(3, 'Born After 1990-09-10', 'Patients Born After 1990-09-10', '{"op": "lt", "field": "dob", "value": "1990-09-10"}'),
(4, 'Born Between 1990-09-10 and 1980-09-10', 'Patients Born Between 1990-09-10 and 1980-09-10', '{"logic":"and","conditions": [{"op": "lt", "field": "dob", "value": "1990-09-10"},{"op": "gt", "field": "dob", "value": "1980-09-10"}]}'),
(5, 'Female Born Between 1990-09-10 and 1980-09-10', 'Female Patients Born Between 1990-09-10 and 1980-09-10', '{"logic":"and","conditions": [{"op": "lt", "field": "dob", "value": "1990-09-10"},{"op": "gt", "field": "dob", "value": "1980-09-10"},{"op": "eq", "field": "gender", "value": "female"}]}');
