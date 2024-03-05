CREATE TABLE user
(
    id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_name  VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    phone      VARCHAR(16),
    role       VARCHAR(20),
    file_path  VARCHAR
);

CREATE TABLE application
(
    id                      INT AUTO_INCREMENT PRIMARY KEY,
    text                    VARCHAR      NOT NULL,
    date                    DATE         NOT NULL,
    status                  VARCHAR(255) NOT NULL,
    last_name_of_shift_head VARCHAR(255) NOT NULL,
    user_id                 INT,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

INSERT INTO user (user_name, password, first_name, last_name, phone, role, file_path)
VALUES ('Grisha23', 'Grisha23', 'Grigory', 'Petrov', '+7(652)654-45-56', 'USER', null),
       ('Alina18', 'Alina18', 'Alina', 'Vorontsova', '+7(652)789-23-54', 'OPERATOR', null),
       ('Natalia45', 'Natalia45', 'Natalia', 'Vladimirova', '+7(652)325-88-88', 'OPERATOR', null),
       ('Andrey33', 'Andrey33', 'Andrey', 'Sokolov', '+7(652)632-66-52', 'ADMIN', null);