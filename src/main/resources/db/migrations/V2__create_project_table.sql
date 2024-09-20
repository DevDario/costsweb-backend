CREATE TABLE IF NOT EXISTS costsweb.project(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    budget DECIMAL(5,3) NOT NULL,
    category ENUM('none','Development','Infrastructure','Planning') NOT NULL DEFAULT 'none',
    serviceID VARCHAR(255),
    numberOfServices INT,
    usedBudget DECIMAL(5,3) NOT NULL DEFAULT 0,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    deadline DATETIME,
    PRIMARY KEY(id),
    FOREIGN KEY(serviceID) REFERENCES service(id),
    FOREIGN KEY(numberOfServices) REFERENCES service(COUNT(id)) 
);