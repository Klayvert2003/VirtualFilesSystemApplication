CREATE TABLE IF NOT EXISTS directory(
    directory_id SERIAL PRIMARY KEY,
    directory_name VARCHAR(255) NOT NULL,
    parent_directory_id INT REFERENCES directory(directory_id) ON DELETE CASCADE,
    created_at DATE DEFAULT CURRENT_DATE
);