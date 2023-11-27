ALTER TABLE event DROP CONSTRAINT fk_event_school_id;
ALTER TABLE event ADD CONSTRAINT fk_event_school_id FOREIGN KEY (school_organizer_id) REFERENCES school (id) ON DELETE SET NULL ;