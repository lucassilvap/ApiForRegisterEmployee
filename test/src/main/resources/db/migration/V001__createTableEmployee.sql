
CREATE TABLE employee (
	id int8 NOT NULL,
	employee_function varchar(255) NOT NULL,
	is_active boolean NOT NULL,
	name varchar(255) NOT NULL,
	salary double NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (id)
);

	