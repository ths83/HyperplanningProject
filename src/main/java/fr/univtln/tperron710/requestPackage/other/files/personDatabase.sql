drop table if exists department;
drop TABLE if EXISTS formation;
drop table if exists grade;
drop table if exists person;
drop table if exists person_id;
drop table if exists promotion;
drop TABLE if exists room;
drop table if exists student_formation;
drop table if exists subject;
drop table if exists teach;
drop table if exists teach_to;
drop table if exists building;
drop table if exists contain;
drop table if exists class;
drop TABLE if EXISTS message;
drop table if exists class_type;

CREATE TABLE class_type
(
  class_type_id integer,
  class_type_name varchar(30),
  CONSTRAINT class_type_key PRIMARY KEY (class_type_id)
)

CREATE TABLE department
(
  department_id smallint NOT NULL,
  department_name varchar(30),
  department_chief_id integer,
  CONSTRAINT departement_pkey PRIMARY KEY (department_id)
);


CREATE TABLE formation
(
  formation_id smallint NOT NULL,
  formation_name varchar(30),
  formation_responsible_id integer,
  formation_department_id integer,
  CONSTRAINT formation_pkey PRIMARY KEY (formation_id)
);

CREATE TABLE grade
(
  grade_id smallint NOT NULL,
  grade_name varchar(30),
  CONSTRAINT grade_pkey PRIMARY KEY (grade_id)
);

CREATE TABLE person
(
  person_id smallint NOT NULL,
  person_first_name varchar(30),
  person_last_name varchar(30),
  person_date_of_birth date,
  person_login varchar(30),
  person_password varchar(30),
  person_role_id integer,
  CONSTRAINT student_pkey PRIMARY KEY (person_id)
);



CREATE TABLE person_id
(
  person_id_id smallint NOT NULL,
  person_id_name varchar(30),
  CONSTRAINT person_id_pkey PRIMARY KEY (person_id_id)
);

CREATE TABLE subject
(
  subject_id smallint NOT NULL,
  subject_name varchar(30),
  subject_responsible_id integer,
  CONSTRAINT matiere_pkey PRIMARY KEY (subject_id)
);

CREATE TABLE building
(
  building_id varchar(5) NOT NULL,
  building_department_id integer,
  CONSTRAINT building_pkey PRIMARY KEY (building_id),
  CONSTRAINT building_department_id_fkey FOREIGN KEY (building_department_id)
  REFERENCES department (department_id)
);

CREATE TABLE contain
(
  contain_formation_id smallint NOT NULL,
  contain_subject_id smallint NOT NULL,
  contain_responsible_id integer,
  CONSTRAINT contain_pkey PRIMARY KEY (contain_formation_id, contain_subject_id),
  CONSTRAINT contain_formation_id_fkey FOREIGN KEY (contain_formation_id)
  REFERENCES formation (formation_id),
  CONSTRAINT contain_responsible_id_fkey FOREIGN KEY (contain_responsible_id)
  REFERENCES person (person_id),
  CONSTRAINT contain_subject_id_fkey FOREIGN KEY (contain_subject_id)
  REFERENCES subject (subject_id)
);

CREATE TABLE promotion
(
  promotion_id integer NOT NULL,
  promotion_formation_id integer NOT NULL,
  promotion_grade_id integer NOT NULL,
  CONSTRAINT promotion_pkey PRIMARY KEY (promotion_id),
  CONSTRAINT promotion_formation_id_pkey FOREIGN KEY (promotion_formation_id)
  REFERENCES formation (formation_id),
  CONSTRAINT promotion_grade_id_pkey FOREIGN KEY (promotion_grade_id)
  REFERENCES grade (grade_id)
);

CREATE TABLE room
(
  room_id integer NOT NULL,
  room_building_id varchar(5),
  CONSTRAINT room_pkey PRIMARY KEY (room_id),
  CONSTRAINT room_building_id_fkey FOREIGN KEY (room_building_id)
  REFERENCES building (building_id)
);

CREATE TABLE student_formation
(
  student_formation_student_id smallint NOT NULL,
  student_formation_formation_id smallint NOT NULL,
  student_formation_grade_id smallint NOT NULL,
  student_formation_is_representative boolean,
  CONSTRAINT studentformation_pkey PRIMARY KEY (student_formation_student_id, student_formation_formation_id, student_formation_grade_id),
  CONSTRAINT studentformation_formation_id_fkey FOREIGN KEY (student_formation_formation_id)
  REFERENCES formation (formation_id),
  CONSTRAINT studentformation_grade_id_fkey FOREIGN KEY (student_formation_grade_id)
  REFERENCES grade (grade_id),
  CONSTRAINT studentformation_student_id_fkey FOREIGN KEY (student_formation_student_id)
  REFERENCES person (person_id)
);

CREATE TABLE teach
(
  teach_subject_id smallint NOT NULL,
  teach_teacher_id smallint NOT NULL,
  CONSTRAINT teach_pkey PRIMARY KEY (teach_teacher_id, teach_subject_id),
  CONSTRAINT teach_subject_id_fkey FOREIGN KEY (teach_subject_id)
  REFERENCES subject (subject_id),
  CONSTRAINT teach_teacher_id_fkey FOREIGN KEY (teach_teacher_id)
  REFERENCES person (person_id)
);

CREATE TABLE teach_to
(
  teach_to_teacher_id integer NOT NULL,
  teach_to_promotion_id integer NOT NULL,
  CONSTRAINT teach_to_pkey PRIMARY KEY (teach_to_teacher_id, teach_to_promotion_id),
  CONSTRAINT teach_to_teach_to_promotion_id_fkey FOREIGN KEY (teach_to_promotion_id)
  REFERENCES promotion (promotion_id),
  CONSTRAINT teach_to_teach_to_teacher_id_fkey FOREIGN KEY (teach_to_teacher_id)
  REFERENCES person (person_id)
);

CREATE TABLE class
(
  class_date date NOT NULL,
  class_begin_hour time NOT NULL,
  class_end_hour time NOT NULL,
  class_subject_id integer NOT NULL,
  class_promotion_id integer NOT NULL,
  class_teacher_id integer NOT NULL,
  class_type_id integer NOT NULL,
  CONSTRAINT class_pkey PRIMARY KEY (class_date, class_heure_debut, class_heure_fin, class_subject_id, class_promotion_id, class_teacher_id),
  CONSTRAINT class_promotion_id_fkey FOREIGN KEY (class_promotion_id)
  REFERENCES promotion (promotion_id),
  CONSTRAINT class_subject_id_fkey FOREIGN KEY (class_subject_id)
  REFERENCES subject (subject_id),
  CONSTRAINT class_teacher_id_fkey FOREIGN KEY (class_teacher_id)
  REFERENCES person (person_id),
  CONSTRAINT class_type_id_fkey FOREIGN KEY (class_type_id)
  REFERENCES class_type(class_type_id)
);

create table message
(
  message_id integer not null,
  message_string varchar (100),
  message_from varchar(10),
  message_to varchar(10),
);

insert into message values(0,'test message','jpoupon997' , 'tperron710');

INSERT INTO CLASS_TYPE(CLASS_TYPE_ID,CLASS_TYPE_NAME)
VALUES
  (0,’magistral lesson’),
  (1,’exercises’),
  (2,’practical work’);

INSERT INTO PUBLIC.DEPARTMENT(DEPARTMENT_ID, DEPARTMENT_NAME, DEPARTMENT_CHIEF_ID) VALUES
  (0, 'Sciences et Techniques', NULL),
  (1, 'STAPS', NULL),
  (2, 'Lettres Modernes', NULL),
  (3, 'Droit', NULL),
  (4, 'Sciences Economiques', NULL);

INSERT INTO PUBLIC.FORMATION(FORMATION_ID, FORMATION_NAME, FORMATION_RESPONSIBLE_ID, FORMATION_DEPARTMENT_ID) VALUES
  (0, 'Chimie', NULL, 0),
  (1, STRINGDECODE('M\u00e9canique'), NULL, 0),
  (2, 'Informatique', NULL, 0),
  (3, STRINGDECODE('Math\u00e9matiques'), NULL, 0),
  (4, 'Electronique', NULL, 0),
  (5, 'STAPS', NULL, 1),
  (6, 'Anglais', NULL, 2),
  (7, 'Chinois', NULL, 2),
  (8, 'Japonais', NULL, 2),
  (9, 'Droit des Entreprises', NULL, 3),
  (10, 'Droit des Affaires', NULL, 3),
  (11, 'Gestion des Entreprises', NULL, 4),
  (12, STRINGDECODE('Sant\u00e9 Publique'), NULL, 4);

INSERT INTO PUBLIC.GRADE(GRADE_ID, GRADE_NAME) VALUES
  (0, 'L1'),
  (1, 'L2'),
  (2, 'L3'),
  (3, 'M1'),
  (4, 'M2');

INSERT INTO PUBLIC.PERSON(PERSON_ID, PERSON_FIRST_NAME, PERSON_LAST_NAME, PERSON_DATE_OF_BIRTH, PERSON_LOGIN, PERSON_PASSWORD, PERSON_ROLE_ID) VALUES
  (0, STRINGDECODE('J\u00e9r\u00e9my'), 'Poupon', DATE '1993-09-12', 'jpoupon997', 'test', 0),
  (1, 'Thomas', 'Perron', DATE '1994-04-29', 'tperron710', 'truc', 0),
  (2, 'Pierre', 'Truc', DATE '1980-01-01', 'ptruc001', 'test', 1),
  (3, 'Paul', 'Machin', DATE '1981-09-25', 'pmachin002', 'truc', 2),
  (4, 'Jacques', 'Bidule', DATE '1985-03-27', 'jbidule105', 'test', 3),
  (5, 'John', 'Smith', DATE '1974-05-19', 'jsmith420', 'truc', 4);

INSERT INTO PUBLIC.PERSON_ID(PERSON_ID_ID, PERSON_ID_NAME) VALUES
  (0, 'etudiant'),
  (1, 'professeur'),
  (2, STRINGDECODE('chef de d\u00e9partement')),
  (3, 'administrateur'),
  (4, STRINGDECODE('responsable de mat\u00e9riel'));


INSERT INTO PUBLIC.PROMOTION(PROMOTION_ID, PROMOTION_FORMATION_ID, PROMOTION_GRADE_ID) VALUES
  (0, 7, 3),
  (1, 8, 4);

INSERT INTO PUBLIC.STUDENT_FORMATION(STUDENT_FORMATION_STUDENT_ID, STUDENT_FORMATION_FORMATION_ID, STUDENT_FORMATION_GRADE_ID, STUDENT_FORMATION_IS_REPRESENTATIVE) VALUES
  (0, 7, 3, FALSE),
  (1, 8, 4, TRUE);

INSERT INTO PUBLIC.SUBJECT(SUBJECT_ID, SUBJECT_NAME, SUBJECT_RESPONSIBLE_ID) VALUES
  (0, 'Anglais', 2),
  (1, STRINGDECODE('Math\u00e9matiques'), NULL),
  (2, 'Programmation', NULL),
  (3, 'Sport', NULL),
  (4, 'Economie', NULL),
  (5, 'Droit', NULL),
  (6, 'LV2', 2),
  (7, 'Sciences', NULL);

INSERT INTO PUBLIC.TEACH(TEACH_SUBJECT_ID, TEACH_TEACHER_ID) VALUES
  (0, 2),
  (6, 2);

INSERT INTO PUBLIC.TEACH_TO(TEACH_TO_TEACHER_ID, TEACH_TO_PROMOTION_ID) VALUES
  (2, 0),
  (2, 1);

INSERT INTO BUILDING VALUES
  ('A', 2),
  ('B', 2);


INSERT INTO PUBLIC.CONTAIN(CONTAIN_FORMATION_ID, CONTAIN_SUBJECT_ID, CONTAIN_RESPONSIBLE_ID) VALUES
  (0, 0, NULL),
  (0, 1, NULL),
  (0, 7, NULL),
  (1, 0, NULL),
  (1, 1, NULL),
  (1, 7, NULL),
  (2, 0, NULL),
  (2, 1, NULL),
  (2, 2, NULL),
  (2, 7, NULL),
  (3, 0, NULL),
  (3, 1, NULL),
  (3, 7, NULL),
  (4, 0, NULL),
  (4, 1, NULL),
  (4, 7, NULL),
  (5, 0, NULL),
  (5, 1, NULL),
  (5, 3, NULL),
  (6, 0, NULL),
  (6, 1, NULL),
  (6, 6, NULL),
  (7, 0, NULL),
  (7, 1, NULL),
  (7, 6, NULL),
  (8, 0, NULL),
  (8, 1, NULL),
  (8, 6, NULL),
  (9, 0, NULL),
  (9, 1, NULL),
  (9, 5, NULL),
  (10, 0, NULL),
  (10, 1, NULL),
  (10, 5, NULL),
  (11, 0, NULL),
  (11, 1, NULL),
  (11, 4, NULL),
  (12, 0, NULL),
  (12, 1, NULL),
  (12, 4, NULL);
