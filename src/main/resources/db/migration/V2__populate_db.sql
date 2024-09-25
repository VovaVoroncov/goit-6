INSERT INTO megasoft.worker (ID, NAME, BIRTHDAY, LEVEL, SALARY)
VALUES
    (1, 'Anastasia', '1984-05-14', 'Intern', 950),
    (2, 'Taras', '1985-06-15', 'Associate', 1600),
    (3, 'Daria', '1986-07-16', 'Lead', 2900),
    (4, 'Oleh', '1987-08-17', 'Intermediate', 2300),
    (5, 'Katya', '1988-09-18', 'Intern', 1150),
    (6, 'Yuriy', '1989-10-19', 'Associate', 1800),
    (7, 'Sofia', '1990-11-20', 'Intermediate', 2600),
    (8, 'Petryk', '1991-12-21', 'Lead', 3700),
    (9, 'Iryna', '1992-01-22', 'Intern', 1200),
    (10, 'Vlad', '1993-02-23', 'Lead', 5800);

INSERT INTO megasoft.client (ID, NAME)
VALUES
    (1, 'Viktoriya'),
    (2, 'Roman'),
    (3, 'Oksana'),
    (4, 'Daryna'),
    (5, 'Igor'),
    (6, 'Maksym'),
    (7, 'Elena'),
    (8, 'Serhii'),
    (9, 'Nadia'),
    (10, 'Artem');

INSERT INTO megasoft.project (ID, CLIENT_ID, START_DATE, FINISH_DATE)
VALUES
    (1, 1, '2024-09-01', '2024-10-20'),
    (2, 2, '2024-09-02', '2024-11-20'),
    (3, 2, '2024-09-03', '2024-12-20'),
    (4, 2, '2024-09-04', '2025-01-20'),
    (5, 5, '2024-09-05', '2025-02-20'),
    (6, 4, '2024-09-06', '2025-03-20'),
    (7, 3, '2024-09-07', '2025-04-20'),
    (8, 6, '2024-09-08', '2025-05-20'),
    (9, 7, '2024-09-09', '2025-06-20'),
    (10, 8, '2024-09-10', '2025-07-20');

INSERT INTO megasoft.project_worker (PROJECT_ID, WORKER_ID)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 4),
    (2, 5),
    (3, 6),
    (4, 7),
    (4, 8),
    (5, 9),
    (6, 10),
    (7, 5),
    (8, 6),
    (9, 7),
    (10, 8);
