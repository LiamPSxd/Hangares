DROP DATABASE hangares;
CREATE DATABASE IF NOT EXISTS hangares;

USE hangares;

CREATE USER IF NOT EXISTS 'hangares'@'localhost' IDENTIFIED BY 'hangares';
GRANT ALL PRIVILEGES ON hangares.* TO 'root'@'localhost';
GRANT ALL PRIVILEGES ON hangares.* TO 'hangares'@'localhost';
FLUSH PRIVILEGES;

SHOW GRANTS FOR root@'localhost';
SHOW GRANTS FOR hangares@'localhost';

SELECT * FROM hangar;
SELECT * FROM hibernate_sequence;