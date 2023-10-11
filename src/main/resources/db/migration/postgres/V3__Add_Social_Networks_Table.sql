CREATE table social_networks
(
    id               uuid                              NOT NULL,
    created          TIMESTAMP                         NOT NULL,
    updated          TIMESTAMP                         NOT NULL,
    instagram        VARCHAR,
    facebook         VARCHAR,
    youtube          VARCHAR,

    CONSTRAINT social_networks_pkey PRIMARY KEY (id)
);

ALTER TABLE dancer ADD COLUMN social_networks_id uuid;
ALTER TABLE dancer ADD CONSTRAINT fk_dancer_social_networks_id FOREIGN KEY (social_networks_id) REFERENCES social_networks (id);

ALTER TABLE event ADD COLUMN social_networks_id uuid;
ALTER TABLE event ADD CONSTRAINT fk_event_social_networks_id FOREIGN KEY (social_networks_id) REFERENCES social_networks (id);

ALTER TABLE school ADD COLUMN social_networks_id uuid;
ALTER TABLE school ADD CONSTRAINT fk_school_social_networks_id FOREIGN KEY (social_networks_id) REFERENCES social_networks (id);