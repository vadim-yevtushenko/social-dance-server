CREATE table rating
(
    id                      UUID                              NOT NULL,
    created                 TIMESTAMP                         NOT NULL,
    updated                 TIMESTAMP                         NOT NULL,
    dancer_id               UUID                              NOT NULL,
    school_id               UUID                              NOT NULL,
    rating                  INTEGER                           NOT NULL,
    CONSTRAINT rating_pkey PRIMARY KEY (id)
);

CREATE table review
(
    id                      UUID                              NOT NULL,
    created                 TIMESTAMP                         NOT NULL,
    updated                 TIMESTAMP                         NOT NULL,
    incognito               BOOLEAN,
    dancer_id               UUID                              NOT NULL,
    school_id               UUID                              NOT NULL,
    rating_id               UUID                              NOT NULL,
    review                  VARCHAR                           NOT NULL,
    CONSTRAINT review_pkey PRIMARY KEY (id),
    FOREIGN KEY (rating_id) REFERENCES rating (id)
);