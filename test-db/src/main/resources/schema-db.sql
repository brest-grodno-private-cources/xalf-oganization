CREATE TABLE IF NOT EXISTS DEPARTMENT
(
    department_id          int         NOT NULL AUTO_INCREMENT,
    department_name        varchar(50) NOT NULL,
    department_description varchar(150),
    PRIMARY KEY (department_id)
);