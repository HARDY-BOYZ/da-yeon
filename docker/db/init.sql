CREATE DATABASE IF NOT EXISTS da-yeon;

CREATE USER 'dayeon'@'%' IDENTIFIED BY 'dayeon';

GRANT ALL PRIVILEGES ON mydatabase.* TO 'dayeon'@'%';

FLUSH PRIVILEGES;