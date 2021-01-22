-- Customer data
insert into customer(first_Name, last_Name,  dob,  email, phone, address, level) values
(
  'John',
  'Smith',
  '1965-09-08',
  'me@me.com',
  '5678499',
  '34 Tasman',
  'Gold'
);
insert into customer(first_Name, last_Name,  dob,  email, phone, address, level) values
(
  'Jane',
  'Doe',
  '1974-06-09',
  'me@me.com',
  '5678567',
  '34 my road',
  'Silver'
);

-- Security data
-- USER
-- hashed password: mypassword
INSERT INTO security_user (id, username, password, first_name, last_name) VALUES
(1,  'admin', '$2a$12$tkAwgt7IPcRHrhXGeOQIwehIe0t4m8yPxAe56.kqrCUSk6Xhpxada', 'Administrator', 'Adminstrator'),
(2,  'Hazel', '$2a$12$tkAwgt7IPcRHrhXGeOQIwehIe0t4m8yPxAe56.kqrCUSk6Xhpxada', 'Hazel', 'Nutt'),
(3,  'Clark', '$2a$12$tkAwgt7IPcRHrhXGeOQIwehIe0t4m8yPxAe56.kqrCUSk6Xhpxada', 'Clark', 'Kent'),
(4,  'Frank', '$2a$12$tkAwgt7IPcRHrhXGeOQIwehIe0t4m8yPxAe56.kqrCUSk6Xhpxada', 'Frank', 'Furter');
-- ROLES

INSERT INTO security_role (id, role_name, description) VALUES (1, 'ROLE_ADMIN', 'Administrator');
INSERT INTO security_role (id, role_name, description) VALUES (2, 'ROLE_CSR', 'Customer Service Representative');

--USER ROLE MAPPING
INSERT INTO user_role(user_id, role_id) VALUES
 (1, 1), -- give admin ROLE_ADMIN
 (2, 2),  -- give Hazel ROLE_CSR
 (3, 2),  -- give Clark ROLE_CSR
 (4, 1),  -- give Frank ROLE_ADMIN
 (4, 2);  -- give Frank ROLE_CSR