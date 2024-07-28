CREATE TABLE routine
(
    routine_id   SERIAL PRIMARY KEY,
    start_time   TIME,
    end_time     TIME,
    routine_date DATE,
    teacher_id   INTEGER NOT NULL,
    group_id     INTEGER NOT NULL,
    subject      VARCHAR(255),
    duration     INTEGER, -- duration in minutes
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id),
    CONSTRAINT fk_group FOREIGN KEY (group_id) REFERENCES "group" (group_id)
);