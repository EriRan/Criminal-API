create schema criminal;

CREATE TABLE criminal.criminal (
	id serial NOT NULL,
	"name" varchar(500) NOT NULL,
	appearance text NULL,
	CONSTRAINT criminal_pk PRIMARY KEY (id)
);
COMMENT ON TABLE criminal.criminal IS 'Contains people who have committed crimes';
COMMENT ON COLUMN criminal.criminal.appearance IS 'What does the criminal look like';

CREATE TABLE criminal.crime_type (
	id serial NOT NULL,
	"name" varchar NOT NULL,
	CONSTRAINT crime_type_pk PRIMARY KEY (id)
);
COMMENT ON TABLE criminal.criminal IS 'Possible crimes that a criminal can commit';

CREATE TABLE criminal.charge (
	id serial NOT NULL,
	description text NOT NULL,
	criminal_id int4 NOT NULL,
	created_at timestamptz(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	charged_at date NOT NULL,
	punished_at date NULL,
	crime_type_id int4 NOT NULL,
	CONSTRAINT charge_pk PRIMARY KEY (id),
	CONSTRAINT charge_crime_type_fk FOREIGN KEY (id) REFERENCES criminal.crime_type(id) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT charge_fk FOREIGN KEY (criminal_id) REFERENCES criminal.criminal(id) ON UPDATE RESTRICT ON DELETE RESTRICT
);
COMMENT ON TABLE criminal.charge IS 'A crime a criminal has been charged with';

CREATE TABLE criminal.sighting (
	id serial NOT NULL,
	criminal_id int4 NOT NULL,
	area varchar(500) NULL,
	address varchar(500) NULL,
	description text NULL,
	created_at timestamptz(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	time_of_sighting timestamptz(0) NOT NULL,
	CONSTRAINT sighting_pk PRIMARY KEY (id),
	CONSTRAINT sighting_fk FOREIGN KEY (criminal_id) REFERENCES criminal.criminal(id) ON UPDATE RESTRICT ON DELETE RESTRICT
);
COMMENT ON TABLE criminal.sighting IS 'Locations where a criminal has been spotted';

-- Initial data

INSERT INTO criminal.crime_type
("name")
VALUES('Financial');
INSERT INTO criminal.crime_type
("name")
VALUES('Murder');
INSERT INTO criminal.crime_type
("name")
VALUES('Unclassified');
INSERT INTO criminal.crime_type
("name")
VALUES('Thievery');

INSERT INTO criminal.criminal
("name", appearance)
VALUES('Osama bin Laden', 'Long black beard');
INSERT INTO criminal.criminal
("name", appearance)
VALUES('Slick Joe', 'Handsome face, white mohawk, red shirt with a text "Ouch!" on it');
INSERT INTO criminal.criminal
("name", appearance)
VALUES('Robert von Lomboc', 'Slim stature. Always wearing a white English judge wig');

INSERT INTO criminal.charge
(description, criminal_id, crime_type_id, charged_at)
VALUES('Terrorism', 1, 2, '2020-11-01');
INSERT INTO criminal.charge
(description, criminal_id, crime_type_id, charged_at)
VALUES('Being way too cool', 2, 3, '2020-11-01');
INSERT INTO criminal.charge
(description, criminal_id, crime_type_id, charged_at)
VALUES('Financial crimes', 3, 1, '2020-11-01');

INSERT INTO criminal.sighting
(criminal_id, area, address, description, time_of_sighting)
VALUES(1, 'New York', '23 South Bulevard', 'Seen buying a bagel', '2020-11-01');
INSERT INTO criminal.sighting
(criminal_id, area, address, description, time_of_sighting)
VALUES(1, 'New York', '10 South Bulevard', E'Loitering at Macy\'s', '2020-05-01');

INSERT INTO criminal.sighting
(criminal_id, area, address, description, time_of_sighting)
VALUES(2, 'London', '10 South Bulevard', 'Bought a donut', '2019-01-01');

INSERT INTO criminal.sighting
(criminal_id, area, address, description, time_of_sighting)
VALUES(3, 'Helsinki', 'Albertinkatu 3', 'Spoke with a stranger before the detectives lost sight of him', '2020-03-01')
;