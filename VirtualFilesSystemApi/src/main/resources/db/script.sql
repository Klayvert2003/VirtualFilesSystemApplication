CREATE TABLE IF NOT EXISTS directory(
    directory_id SERIAL PRIMARY KEY,
    directory_name VARCHAR(255) NOT NULL,
    parent_directory_id INT REFERENCES directory(directory_id) ON DELETE CASCADE,
    created_at DATE DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS file(
    file_id SERIAL PRIMARY KEY,
    file_name VARCHAR(255) NOT NULl,
    directory_id INT REFERENCES directory(directory_id) ON DELETE CASCADE,
    created_at DATE DEFAULT CURRENT_DATE
);

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

INSERT INTO
    file(file_name, directory_id)
VALUES
    ('user.txt', 3),
    ('klayvert.jpg', 4),
    ('Desenvolvedor Java Pleno.pptx', 5),
    ('Curriculo-KlayvertAlves.pdf', 6),
    ('windows.conf', 7),
    ('system-uninstall.exe', 8),
    ('Valorant.exe', 9),
    ('CSGO.exe', 9),
    ('teste.mp4', 10),
    ('teste2.mp4', 10);