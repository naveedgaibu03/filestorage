

-- Drop existing tables (if needed)
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS files;

-- Create tables
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       roles VARCHAR(50) DEFAULT 'ROLE_USER'
);

CREATE TABLE files (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       content_type VARCHAR(100),
                       data BLOB,
                       owner VARCHAR(50),
                       version INT DEFAULT 1
);

-- Insert initial data if needed
INSERT INTO users (username, password) VALUES ('admin', '$2a$10$9zI0Y/D6ve.7ShM5k8kTveGMNhiPJLWppD.NWc3EzFYJHd78lN1yK');
