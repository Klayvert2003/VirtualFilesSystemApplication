INSERT INTO
    directory(directory_name)
VALUES
    ('C:'),
    ('D:');

INSERT INTO
    directory(directory_name, parent_directory_id)
VALUES
    ('Users', 1),
    ('Klayvert', 3),
    ('Download', 1),
    ('Documents', 1),
    ('Windows', 1),
    ('System32', 7),
    ('Games', 2),
    ('Videos', 2);