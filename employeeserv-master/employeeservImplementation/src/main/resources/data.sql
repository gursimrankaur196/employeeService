DROP TABLE IF EXISTS billionaires;

CREATE TABLE employeeEntity (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
);

INSERT INTO employeeEntity (first_name, last_name) VALUES
  ('Aliko', 'Dangote'),
  ('Bill', 'Gates'),
  ('Folrunsho', 'Alakija');