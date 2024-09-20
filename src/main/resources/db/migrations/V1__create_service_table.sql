CREATE TABLE IF NOT EXISTS costsweb.service(
    id INT NOT NULL AUTO_INCREMENT,
    projectID INT,
    name VARCHAR(255) NOT NULL,
    budget DECIMAL(5,3) NOT NULL,
    description VARCHAR(255) DEFAULT 'none',
    numberOfServices INT NOT NULL DEFAULT 0,
    CONSTRAINT PRIMARY KEY(id),
    FOREIGN KEY(projectID) REFERENCES project(id)
);