INSERT INTO DEPARTMENT(department_name, department_description) VALUES('IT', 'IT SUPPORT');
INSERT INTO DEPARTMENT(department_name, department_description) VALUES('SECURITY', 'SECURITY');

INSERT INTO EMPLOYEE(first_name, last_name, email, start_date, department_id) VALUES('IVAN', 'IVANOV', 'IVAN@GMAIL.COM', PARSEDATETIME('14-11-2022', 'dd-MM-yyyy'), 1);