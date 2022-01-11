drop table if exists dances;
drop table if exists abstract_base_entity_dances;
drop table if exists reviews;
drop table if exists schools;
drop table if exists dancers;
drop table if exists events;
drop table if exists ratings;
drop table if exists abstract_base_entity;
drop table if exists entity_info;
drop table if exists login_password;


drop sequence if exists schools_seq;
drop sequence if exists dancers_seq;
drop sequence if exists events_seq;
drop sequence if exists dances_seq;
drop sequence if exists entity_info_seq;
drop sequence if exists ratings_seq;
drop sequence if exists reviews_seq;
drop sequence if exists base_seq;
drop sequence if exists hibernate_sequence;
drop sequence if exists login_password_seq;

CREATE SEQUENCE hibernate_sequence ;

CREATE SEQUENCE login_password_seq START WITH 1;
CREATE table login_password
(
    id                      INTEGER PRIMARY KEY DEFAULT nextval('login_password_seq'),
    email            VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    CONSTRAINT email_password UNIQUE (email)
);

CREATE SEQUENCE entity_info_seq ;
CREATE table entity_info
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('entity_info_seq'),
    country          VARCHAR,
    city             VARCHAR,
    street           VARCHAR,
    building         VARCHAR,
    suites           VARCHAR,
    phone_number     VARCHAR,
    email            VARCHAR
);

CREATE SEQUENCE base_seq ;
create table abstract_base_entity
(
    id                           INTEGER PRIMARY KEY DEFAULT nextval('base_seq'),
    name                         VARCHAR                           NOT NULL,
    description                  VARCHAR,
    type_entity                  VARCHAR,
    entity_info_id               INTEGER,
    CONSTRAINT id_constr UNIQUE (id),
    FOREIGN KEY (entity_info_id) REFERENCES entity_info (id) ON DELETE CASCADE
);

CREATE SEQUENCE schools_seq ;
CREATE table schools
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('schools_seq'),
    owner_id         INTEGER,
    FOREIGN KEY (id) REFERENCES abstract_base_entity (id) ON DELETE CASCADE
);


CREATE SEQUENCE dancers_seq ;
CREATE table dancers
(
    id                  INTEGER PRIMARY KEY DEFAULT nextval('dancers_seq'),
    surname             VARCHAR,
    sex                 VARCHAR,
    birthday            TIMESTAMP,
    role                VARCHAR,
    login_password_id   INTEGER,
    FOREIGN KEY (id) REFERENCES abstract_base_entity (id) ON DELETE CASCADE,
    FOREIGN KEY (login_password_id) REFERENCES login_password (id) ON DELETE CASCADE
);

CREATE SEQUENCE events_seq ;
CREATE table events
(
    id                 INTEGER PRIMARY KEY DEFAULT nextval('events_seq'),
    owner_id           INTEGER,
    date_event         TIMESTAMP                           NOT NULL,
    date_finish_event  TIMESTAMP                           NOT NULL,
    date_publication   TIMESTAMP             DEFAULT now() NOT NULL,
    FOREIGN KEY (id) REFERENCES abstract_base_entity (id) ON DELETE CASCADE
);

CREATE SEQUENCE dances_seq START WITH 1;
CREATE table dances
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('dances_seq'),
    name VARCHAR  NOT NULL
);

CREATE table abstract_base_entity_dances
(
    entity_id integer not null,
    dance     varchar,
    FOREIGN KEY (entity_id) REFERENCES abstract_base_entity (id) ON DELETE CASCADE
);

CREATE SEQUENCE ratings_seq START WITH 1;
CREATE table ratings
(
    id                      INTEGER PRIMARY KEY DEFAULT nextval('ratings_seq'),
    abstract_base_entity_id INTEGER  NOT NULL,
    reviewer_id             INTEGER  NOT NULL,
    rating                  INTEGER  NOT NULL,
    CONSTRAINT id_con UNIQUE (id),
    FOREIGN KEY (abstract_base_entity_id) REFERENCES abstract_base_entity (id) ON DELETE CASCADE
);

CREATE SEQUENCE reviews_seq START WITH 1;
CREATE table reviews
(
    id                      INTEGER PRIMARY KEY DEFAULT nextval('reviews_seq'),
    abstract_base_entity_id INTEGER    NOT NULL,
    school_id               INTEGER    NOT NULL,
    review                  varchar    NOT NULL,
    date_time               TIMESTAMP  NOT NULL,
    FOREIGN KEY (school_id) REFERENCES schools (id) ON DELETE CASCADE
);

ALTER TABLE abstract_base_entity
    ADD COLUMN image varchar;