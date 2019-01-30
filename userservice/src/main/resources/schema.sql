DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL
);

INSERT INTO users (name, email) VALUES ('Ron', 'ron@bar.com');

INSERT INTO users (name, email) VALUES ('Renee', 'renee@bar.com');