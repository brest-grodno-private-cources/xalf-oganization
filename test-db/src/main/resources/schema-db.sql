CREATE TABLE IF NOT EXISTS DEPARTMENT
(
    department_id          int         NOT NULL AUTO_INCREMENT,
    department_name        varchar(50) NOT NULL,
    department_description varchar(150),
    PRIMARY KEY (department_id)
);

CREATE TABLE IF NOT EXISTS EMPLOYEE
(
    employee_id       int NOT NULL AUTO_INCREMENT,
    first_name        varchar(50)  NOT NULL,
    last_name         varchar(50) NOT NULL,
    email             varchar(50) NOT NULL,
    start_date        date NOT NULL,
    department_id     int NOT NULL,
    PRIMARY KEY (employee_id),
    FOREIGN KEY (department_id) REFERENCES DEPARTMENT(department_id)
);