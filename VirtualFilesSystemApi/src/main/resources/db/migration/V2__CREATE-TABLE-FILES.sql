CREATE TABLE IF NOT EXISTS file(
    file_id SERIAL PRIMARY KEY,
    file_name VARCHAR(255) NOT NULl,
    directory_id INT REFERENCES directory(directory_id) ON DELETE CASCADE,
    created_at DATE DEFAULT CURRENT_DATE
);