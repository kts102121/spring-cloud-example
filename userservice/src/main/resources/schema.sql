DROP TABLE IF EXISTS users;

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    user_name TEXT NOT NULL,
    user_email TEXT NOT NULL
);

INSERT INTO users (user_name, user_email) VALUES ('Ron', 'ron@bar.com');

INSERT INTO users (user_name, user_email) VALUES ('Renee', 'renee@bar.com');