DROP TABLE IF EXISTS student;

CREATE TABLE student (
    id serial NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL
);

INSERT INTO student(first_name, last_name) VALUES('Tony', 'Parker');
INSERT INTO student(first_name, last_name) VALUES('Bruce', 'Wayne');