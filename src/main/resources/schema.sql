CREATE TABLE customer(
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   first_Name VARCHAR(50) NOT NULL,
   last_Name VARCHAR(50) NOT NULL,
   dob date NOT NULL,
   email VARCHAR(20) NOT NULL,
   phone VARCHAR(10) NOT NULL,
   address VARCHAR(50) NOT NULL,
   level VARCHAR(16)
);