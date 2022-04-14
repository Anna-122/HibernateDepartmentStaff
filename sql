CREATE TABLE departments
(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    department_name VARCHAR(20) NOT NULL unique
);

CREATE TABLE employees
(
    id            INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    employee_name          VARCHAR(20)                    NULL,
    employee_surname       VARCHAR(20)                    NULL,
    employee_date_birthday DATE,
    employee_phone_number  VARCHAR(15),
    employee_email         VARCHAR(256),
    department_id          INT  NOT NULL REFERENCES departments (department_name)
);

ALTER TABLE employees
    ADD FOREIGN KEY (id) REFERENCES departments (id) ON DELETE CASCADE ;