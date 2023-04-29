drop table if exists dance;
drop table if exists dancer_has_dances;
drop table if exists school_has_dances;
drop table if exists event_has_dances;
drop table if exists review;
drop table if exists school;
drop table if exists dancer;
drop table if exists event;
drop table if exists rating;
drop table if exists entity_info;
drop table if exists credential;


drop sequence if exists school_seq;
drop sequence if exists dancer_seq;
drop sequence if exists event_seq;
drop sequence if exists dance_seq;
drop sequence if exists entity_info_seq;
drop sequence if exists rating_seq;
drop sequence if exists review_seq;
drop sequence if exists hibernate_sequence;
drop sequence if exists credential_seq;

CREATE SEQUENCE hibernate_sequence ;

CREATE SEQUENCE credential_seq;
CREATE table credential
(
    id               uuid                              NOT NULL,
    created          TIMESTAMP                         NOT NULL,
    updated          TIMESTAMP                         NOT NULL,
    email            VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    CONSTRAINT credential_pkey PRIMARY KEY (id),
    CONSTRAINT email_password UNIQUE (email)
);

CREATE SEQUENCE entity_info_seq;
CREATE table entity_info
(
    id               uuid                              NOT NULL,
    created          TIMESTAMP                         NOT NULL,
    updated          TIMESTAMP                         NOT NULL,
    country          VARCHAR,
    city             VARCHAR,
    street           VARCHAR,
    building         VARCHAR,
    suites           VARCHAR,
    phone_number     VARCHAR,
    email            VARCHAR,
    CONSTRAINT entity_info_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE school_seq;
CREATE table school
(
    id                           uuid                              NOT NULL,
    created                      TIMESTAMP                         NOT NULL,
    updated                      TIMESTAMP                         NOT NULL,
    name                         VARCHAR                           NOT NULL,
    description                  VARCHAR,
    entity_info_id               uuid,
    image                        VARCHAR,
    CONSTRAINT school_pkey PRIMARY KEY (id),
    FOREIGN KEY (entity_info_id) REFERENCES entity_info (id) ON DELETE CASCADE
);


CREATE SEQUENCE dancer_seq;
CREATE table dancer
(
    id                  uuid                              NOT NULL,
    created             TIMESTAMP                         NOT NULL,
    updated             TIMESTAMP                         NOT NULL,
    name                VARCHAR                           NOT NULL,
    description         VARCHAR,
    entity_info_id      uuid,
    last_name           VARCHAR,
    gender              VARCHAR,
    birthday            TIMESTAMP,
    role                VARCHAR,
    credential_id       uuid,
    image               VARCHAR,
    CONSTRAINT dancer_pkey PRIMARY KEY (id),
    FOREIGN KEY (entity_info_id) REFERENCES entity_info (id) ON DELETE CASCADE,
    FOREIGN KEY (credential_id) REFERENCES credential (id) ON DELETE CASCADE
);

CREATE SEQUENCE event_seq;
CREATE table event
(
    id                  uuid                              NOT NULL,
    created             TIMESTAMP                         NOT NULL,
    updated             TIMESTAMP                         NOT NULL,
    name                VARCHAR                           NOT NULL,
    description         VARCHAR,
    entity_info_id      uuid,
    date_event          TIMESTAMP                         NOT NULL,
    date_finish_event   TIMESTAMP                         NOT NULL,
    image               VARCHAR,
    CONSTRAINT event_pkey PRIMARY KEY (id),
    FOREIGN KEY (entity_info_id) REFERENCES entity_info (id) ON DELETE CASCADE
);

CREATE SEQUENCE dance_seq START WITH 1;
CREATE table dance
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('dance_seq'),
    name VARCHAR  NOT NULL
);

CREATE table dancer_has_dances
(
    dancer_id uuid NOT NULL,
    dance     VARCHAR,
    FOREIGN KEY (dancer_id) REFERENCES dancer (id) ON DELETE CASCADE
);

CREATE table event_has_dances
(
    event_id  uuid NOT NULL,
    dance     VARCHAR,
    FOREIGN KEY (event_id) REFERENCES event (id) ON DELETE CASCADE
);

CREATE table school_has_dances
(
    school_id uuid NOT NULL,
    dance     VARCHAR,
    FOREIGN KEY (school_id) REFERENCES school (id) ON DELETE CASCADE
);

CREATE SEQUENCE rating_seq;
CREATE table rating
(
    id                      uuid                              NOT NULL,
    created                 TIMESTAMP                         NOT NULL,
    updated                 TIMESTAMP                         NOT NULL,
    base_dance_entity_id    VARCHAR                           NOT NULL,
    rating_owner_id         VARCHAR                           NOT NULL,
    rating                  INTEGER                           NOT NULL,
    CONSTRAINT rating_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE review_seq;
CREATE table review
(
    id                      uuid                              NOT NULL,
    created                 TIMESTAMP                         NOT NULL,
    updated                 TIMESTAMP                         NOT NULL,
    review_owner            uuid                              NOT NULL,
    incognito               BOOLEAN,
    base_dance_entity_id    VARCHAR                           NOT NULL,
    review                  VARCHAR                           NOT NULL,
    CONSTRAINT review_pkey PRIMARY KEY (id),
    FOREIGN KEY (review_owner) REFERENCES dancer (id) ON DELETE CASCADE
);