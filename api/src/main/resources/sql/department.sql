SET FOREIGN_KEY_CHECKS=0;
INSERT INTO ehr_department(`name`,`retired`,`created_on`,`created_by`)
VALUES
       ('Registration', 0, CURRENT_DATE, 1),
       ('Laboratory', 0, CURRENT_DATE, 1),
       ('Radiology', 0, CURRENT_DATE, 1),
       ('Procedure', 0, CURRENT_DATE, 1),
       ('Pharmacy', 0, CURRENT_DATE, 1),
       ('NHIF', 0, CURRENT_DATE, 1),
       ('Student', 0, CURRENT_DATE, 1);

SET FOREIGN_KEY_CHECKS=1;
