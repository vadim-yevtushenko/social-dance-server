ALTER TABLE event DROP CONSTRAINT fk_event_school_id;
ALTER TABLE event ADD CONSTRAINT fk_event_school_id FOREIGN KEY (school_organizer_id) REFERENCES school (id) ON DELETE SET NULL ;

ALTER TABLE city DROP CONSTRAINT city_country_id_fkey;
ALTER TABLE city ADD CONSTRAINT city_country_id_fkey FOREIGN KEY (country_id) REFERENCES country (id) ON DELETE CASCADE;