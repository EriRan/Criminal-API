create schema criminal;

CREATE TABLE criminal.criminal (
	id serial NOT NULL,
	"name" varchar(500) NOT NULL,
	appearance text NULL,
	CONSTRAINT criminal_pk PRIMARY KEY (id)
);
COMMENT ON TABLE criminal.criminal IS 'Contains people who have committed crimes';
COMMENT ON COLUMN criminal.criminal.appearance IS 'What does the criminal look like';

CREATE TABLE criminal.charge (
	id serial NOT NULL,
	description text NOT NULL,
	criminal_id int4 NOT NULL,
	CONSTRAINT charge_pk PRIMARY KEY (id),
	CONSTRAINT charge_fk FOREIGN KEY (criminal_id) REFERENCES criminal.criminal(id) ON UPDATE RESTRICT ON DELETE RESTRICT
);
COMMENT ON TABLE criminal.charge IS 'A crime a criminal has been charged with';

-- Initial data

INSERT INTO criminal.criminal
("name", appearance)
VALUES('Osama bin Laden', 'Long black beard');
INSERT INTO criminal.criminal
("name", appearance)
VALUES('Slick Joe', 'Handsome face, white mohawk, red shirt with a text "Ouch!" on it');
INSERT INTO criminal.criminal
("name", appearance)
VALUES('Robert von Lomboc', 'Slim stature. Always wearing white English judge wig');

INSERT INTO criminal.charge
(description, criminal_id)
VALUES('Terrorism', 1);
INSERT INTO criminal.charge
(description, criminal_id)
VALUES('Being way too cool', 2);
INSERT INTO criminal.charge
(description, criminal_id)
VALUES('Financial crimes', 3);