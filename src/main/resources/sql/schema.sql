CREATE TABLE IF NOT EXISTS department (
department_id serial PRIMARY KEY,
name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS employee (
	employee_id serial PRIMARY KEY,
	first_name VARCHAR NOT NULL,
	last_name VARCHAR NOT NULL,
	department_id INT NOT NULL references department,
	job_title VARCHAR NOT NULL,
	gender VARCHAR NOT NULL,
	date_of_birth VARCHAR NOT NULL
);