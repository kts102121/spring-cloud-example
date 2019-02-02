DROP TABLE IF EXISTS inventory;

CREATE TABLE inventory (
    inventory_id SERIAL PRIMARY KEY,
    user_id INTEGER,
    inventory_name VARCHAR(1000)
);

INSERT INTO inventory (user_id, inventory_name) VALUES ('1', 'Oreo');

INSERT INTO inventory (user_id, inventory_name) VALUES ('2', 'Pringles');