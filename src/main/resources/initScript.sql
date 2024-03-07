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

INSERT INTO users (user_name, password, first_name, last_name, phone, role)
VALUES ('Grisha23', 'Grisha23', 'Grigory', 'Petrov', '+7(652)654-45-56', 0),
       ('Alina18', 'Alina18', 'Alina', 'Vorontsova', '+7(652)789-23-54', 1),
       ('Natalia45', 'Natalia45', 'Natalia', 'Vladimirova', '+7(652)325-88-88', 1),
       ('Andrey33', 'Andrey33', 'Andrey', 'Sokolov', '+7(652)632-66-52', 2);

INSERT INTO Application (status, text, date, phone, user_name, users_id)
VALUES (0, 'Text for application 1', '2024-03-04 09:23:40', '+12345678901', 'User_1', 1),
       (0, 'Text for application 2', '2024-02-29 18:40:15', '+12345678902', 'User_2', 2),
       (1, 'Text for application 3', '2024-03-09 07:12:30', '+12345678903', 'User_3', 3),
       (1, 'Text for application 4', '2024-03-01 23:55:18', '+12345678904', 'User_4', 4),
       (2, 'Text for application 5', '2024-03-02 15:30:45', '+12345678905', 'User_5', 1),
       (2, 'Text for application 6', '2024-03-07 12:05:20', '+12345678906', 'User_6', 2),
       (0, 'Text for application 7', '2024-03-03 04:48:36', '+12345678907', 'User_7', 3),
       (0, 'Text for application 8', '2024-03-06 21:20:59', '+12345678908', 'User_8', 4),
       (0, 'Text for application 9', '2024-03-05 10:33:25', '+12345678909', 'User_9', 1),
       (0, 'Text for application 10', '2024-03-08 16:45:55', '+12345678910', 'User_10', 2);

SELECT *
FROM application AS a
ORDER BY a.date DESC
OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY;

SELECT *
FROM application AS a
WHERE status = 1;

SELECT * FROM (SELECT * FROM application AS a WHERE status = 0) AS a ORDER BY a.date DESC OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY;