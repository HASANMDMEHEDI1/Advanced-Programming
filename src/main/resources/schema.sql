CREATE TABLE continents (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE countries (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           code CHAR(3) NOT NULL UNIQUE,
                           continent_id INT NOT NULL,
                           FOREIGN KEY (continent_id) REFERENCES continents(id)
);

CREATE TABLE cities (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        country_id INT NOT NULL,
                        capital BOOLEAN NOT NULL,
                        latitude DOUBLE NOT NULL,
                        longitude DOUBLE NOT NULL,
                        FOREIGN KEY (country_id) REFERENCES countries(id)
);