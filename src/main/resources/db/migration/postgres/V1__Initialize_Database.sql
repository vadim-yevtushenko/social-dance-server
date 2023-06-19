-- drop table if exists dancers_has_dances;
-- drop table if exists schools_has_dances;
-- drop table if exists events_has_dances;
-- drop table if exists school_has_administrators;
-- drop table if exists school_has_teachers;
-- drop table if exists schools_has_students;
-- drop table if exists events_has_organizers;
-- drop table if exists events_has_dancers;
-- drop table if exists review;
-- drop table if exists credential;
-- drop table if exists dancer;
-- drop table if exists school;
-- drop table if exists event;
-- drop table if exists dance;
-- drop table if exists rating;
-- drop table if exists contact_info;
-- drop table if exists city;
-- drop table if exists country;

-- drop sequence if exists school_seq;
-- drop sequence if exists dancer_seq;
-- drop sequence if exists event_seq;
-- drop sequence if exists dance_seq;
-- drop sequence if exists contact_info_seq;
-- drop sequence if exists rating_seq;
-- drop sequence if exists review_seq;
-- drop sequence if exists hibernate_sequence;
-- drop sequence if exists credential_seq;
-- drop sequence if exists city_seq;
-- drop sequence if exists country_seq;

CREATE SEQUENCE hibernate_sequence ;

CREATE SEQUENCE contact_info_seq;
CREATE table contact_info
(
    id               uuid                              NOT NULL,
    created          TIMESTAMP                         NOT NULL,
    updated          TIMESTAMP                         NOT NULL,
    country          VARCHAR,
    city             VARCHAR,
    street           VARCHAR,
    building         VARCHAR,
    flat             VARCHAR,
    phone_number     VARCHAR,
    email            VARCHAR,
    CONSTRAINT contact_info_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE dance_seq START WITH 1;
CREATE table dance
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('dance_seq'),
    name VARCHAR  NOT NULL
);



CREATE SEQUENCE school_seq;
CREATE table school
(
    id                           uuid                              NOT NULL,
    created                      TIMESTAMP                         NOT NULL,
    updated                      TIMESTAMP                         NOT NULL,
    name                         VARCHAR                           NOT NULL,
    description                  VARCHAR,
    contact_info_id              uuid,
    image                        VARCHAR,
    CONSTRAINT school_pkey PRIMARY KEY (id),
    FOREIGN KEY (contact_info_id) REFERENCES contact_info (id) ON DELETE CASCADE
);

CREATE table schools_has_dances
(
    school_id    uuid NOT NULL,
    dance_id     int NOT NULL,
    CONSTRAINT schools_has_dances_pkey PRIMARY KEY (school_id, dance_id),
    FOREIGN KEY (school_id) REFERENCES school (id),
    FOREIGN KEY (dance_id) REFERENCES dance (id)
);



CREATE SEQUENCE dancer_seq;
CREATE table dancer
(
    id                  uuid                              NOT NULL,
    created             TIMESTAMP                         NOT NULL,
    updated             TIMESTAMP                         NOT NULL,
    name                VARCHAR                           NOT NULL,
    last_name           VARCHAR,
    gender              VARCHAR,
    birthday            TIMESTAMP,
    description         VARCHAR,
    contact_info_id     uuid                                  NULL,
    level               VARCHAR,
    image               VARCHAR,
    CONSTRAINT dancer_pkey PRIMARY KEY (id),
    FOREIGN KEY (contact_info_id) REFERENCES contact_info (id)
);

CREATE table dancers_has_dances
(
    dancer_id    uuid NOT NULL,
    dance_id     int NOT NULL,
    CONSTRAINT dancers_has_dances_pkey PRIMARY KEY (dancer_id, dance_id),
    FOREIGN KEY (dancer_id) REFERENCES dancer (id),
    FOREIGN KEY (dance_id) REFERENCES dance (id)
);



CREATE SEQUENCE event_seq;
CREATE table event
(
    id                      uuid                              NOT NULL,
    created                 TIMESTAMP                         NOT NULL,
    updated                 TIMESTAMP                         NOT NULL,
    name                    VARCHAR                           NOT NULL,
    description             VARCHAR,
    contact_info_id         uuid,
    school_organizer_id     uuid,
    date_event              TIMESTAMP                         NOT NULL,
    date_finish_event       TIMESTAMP                         NOT NULL,
    image                   VARCHAR,
    CONSTRAINT event_pkey PRIMARY KEY (id),
    FOREIGN KEY (contact_info_id) REFERENCES contact_info (id),
    FOREIGN KEY (school_organizer_id) REFERENCES school (id)
);

CREATE table events_has_dances
(
    event_id     uuid NOT NULL,
    dance_id     int NOT NULL,
    CONSTRAINT events_has_dances_pkey PRIMARY KEY (event_id, dance_id),
    FOREIGN KEY (event_id) REFERENCES event (id),
    FOREIGN KEY (dance_id) REFERENCES dance (id)
);

CREATE table events_has_organizers
(
    event_id      uuid NOT NULL,
    dancer_id     uuid NOT NULL,
    CONSTRAINT events_has_organizers_pkey PRIMARY KEY (event_id, dancer_id),
    FOREIGN KEY (event_id) REFERENCES event (id),
    FOREIGN KEY (dancer_id) REFERENCES dancer (id)
);

CREATE table events_has_dancers
(
    event_id      uuid NOT NULL,
    dancer_id     uuid NOT NULL,
    CONSTRAINT events_has_dancers_pkey PRIMARY KEY (event_id, dancer_id),
    FOREIGN KEY (event_id) REFERENCES event (id),
    FOREIGN KEY (dancer_id) REFERENCES dancer (id)
);



CREATE table school_has_administrators
(
    school_id     uuid NOT NULL,
    dancer_id     uuid NOT NULL,
    CONSTRAINT school_has_administrators_pkey PRIMARY KEY (school_id, dancer_id),
    FOREIGN KEY (school_id) REFERENCES school (id),
    FOREIGN KEY (dancer_id) REFERENCES dancer (id)
);

CREATE table school_has_teachers
(
    school_id     uuid NOT NULL,
    dancer_id     uuid NOT NULL,
    CONSTRAINT school_has_teachers_pkey PRIMARY KEY (school_id, dancer_id),
    FOREIGN KEY (school_id) REFERENCES school (id),
    FOREIGN KEY (dancer_id) REFERENCES dancer (id)
);

CREATE table schools_has_students
(
    school_id     uuid NOT NULL,
    dancer_id     uuid NOT NULL,
    CONSTRAINT schools_has_students_pkey PRIMARY KEY (school_id, dancer_id),
    FOREIGN KEY (school_id) REFERENCES school (id),
    FOREIGN KEY (dancer_id) REFERENCES dancer (id)
);



CREATE SEQUENCE credential_seq;
CREATE table credential
(
    id               uuid                              NOT NULL,
    created          TIMESTAMP                         NOT NULL,
    updated          TIMESTAMP                         NOT NULL,
    dancer_id        uuid                              NOT NULL,
    email            VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    CONSTRAINT credential_pkey PRIMARY KEY (id),
    CONSTRAINT credential_email UNIQUE (email),
    FOREIGN KEY (dancer_id) REFERENCES dancer (id)
);



-- CREATE SEQUENCE rating_seq;
-- CREATE table rating
-- (
--     id                      uuid                              NOT NULL,
--     created                 TIMESTAMP                         NOT NULL,
--     updated                 TIMESTAMP                         NOT NULL,
--     base_dance_entity_id    VARCHAR                           NOT NULL,
--     rating_owner_id         VARCHAR                           NOT NULL,
--     rating                  INTEGER                           NOT NULL,
--     CONSTRAINT rating_pkey PRIMARY KEY (id)
-- );
--
-- CREATE SEQUENCE review_seq;
-- CREATE table review
-- (
--     id                      uuid                              NOT NULL,
--     created                 TIMESTAMP                         NOT NULL,
--     updated                 TIMESTAMP                         NOT NULL,
--     review_owner            uuid                              NOT NULL,
--     incognito               BOOLEAN,
--     base_dance_entity_id    VARCHAR                           NOT NULL,
--     review                  VARCHAR                           NOT NULL,
--     CONSTRAINT review_pkey PRIMARY KEY (id),
--     FOREIGN KEY (review_owner) REFERENCES dancer (id) ON DELETE CASCADE
-- );

CREATE SEQUENCE country_seq START WITH 1;
CREATE table country
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('country_seq'),
    name VARCHAR  NOT NULL,
    iso2 VARCHAR,
    iso3 VARCHAR,
    CONSTRAINT unique_name UNIQUE (name)
);

CREATE SEQUENCE city_seq START WITH 1;
CREATE table city
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('city_seq'),
    name VARCHAR  NOT NULL,
    country_id INTEGER,
    admin_name VARCHAR,
    status VARCHAR,
    FOREIGN KEY (country_id) REFERENCES country (id)
);

INSERT INTO dance values (1, 'Salsa'),
                         (2, 'Bachata'),
                         (3, 'Kizomba'),
                         (4, 'Zouk'),
                         (5, 'Merenge'),
                         (6, 'Reggaeton'),
                         (7, 'Argentine Tango'),
                         (8, 'Mambo');
