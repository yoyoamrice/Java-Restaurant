INSERT IGNORE INTO vets VALUES (1, 'James', 'Carter');
INSERT IGNORE INTO vets VALUES (2, 'Helen', 'Leary');
INSERT IGNORE INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT IGNORE INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT IGNORE INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT IGNORE INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT IGNORE INTO specialties VALUES (1, 'radiology');
INSERT IGNORE INTO specialties VALUES (2, 'surgery');
INSERT IGNORE INTO specialties VALUES (3, 'dentistry');

INSERT IGNORE INTO vet_specialties VALUES (2, 1);
INSERT IGNORE INTO vet_specialties VALUES (3, 2);
INSERT IGNORE INTO vet_specialties VALUES (3, 3);
INSERT IGNORE INTO vet_specialties VALUES (4, 2);
INSERT IGNORE INTO vet_specialties VALUES (5, 1);

INSERT IGNORE INTO types VALUES (1, 'cat');
INSERT IGNORE INTO types VALUES (2, 'dog');
INSERT IGNORE INTO types VALUES (3, 'lizard');
INSERT IGNORE INTO types VALUES (4, 'snake');
INSERT IGNORE INTO types VALUES (5, 'bird');
INSERT IGNORE INTO types VALUES (6, 'hamster');

INSERT IGNORE INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023');
INSERT IGNORE INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749');
INSERT IGNORE INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763');
INSERT IGNORE INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198');
INSERT IGNORE INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765');
INSERT IGNORE INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654');
INSERT IGNORE INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387');
INSERT IGNORE INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683');
INSERT IGNORE INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435');
INSERT IGNORE INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487');

INSERT IGNORE INTO pets VALUES (1, 'Leo', '2000-09-07', 1, 1);
INSERT IGNORE INTO pets VALUES (2, 'Basil', '2002-08-06', 6, 2);
INSERT IGNORE INTO pets VALUES (3, 'Rosy', '2001-04-17', 2, 3);
INSERT IGNORE INTO pets VALUES (4, 'Jewel', '2000-03-07', 2, 3);
INSERT IGNORE INTO pets VALUES (5, 'Iggy', '2000-11-30', 3, 4);
INSERT IGNORE INTO pets VALUES (6, 'George', '2000-01-20', 4, 5);
INSERT IGNORE INTO pets VALUES (7, 'Samantha', '1995-09-04', 1, 6);
INSERT IGNORE INTO pets VALUES (8, 'Max', '1995-09-04', 1, 6);
INSERT IGNORE INTO pets VALUES (9, 'Lucky', '1999-08-06', 5, 7);
INSERT IGNORE INTO pets VALUES (10, 'Mulligan', '1997-02-24', 2, 8);
INSERT IGNORE INTO pets VALUES (11, 'Freddy', '2000-03-09', 5, 9);
INSERT IGNORE INTO pets VALUES (12, 'Lucky', '2000-06-24', 2, 10);
INSERT IGNORE INTO pets VALUES (13, 'Sly', '2002-06-08', 1, 10);

INSERT IGNORE INTO visits VALUES (1, 7, '2010-03-04', 'rabies shot');
INSERT IGNORE INTO visits VALUES (2, 8, '2011-03-04', 'rabies shot');
INSERT IGNORE INTO visits VALUES (3, 8, '2009-06-04', 'neutered');
INSERT IGNORE INTO visits VALUES (4, 7, '2008-09-04', 'spayed');

INSERT IGNORE INTO roles (name, description) VALUES
   ('SCHOOL_ADMIN', 'Rec Center Admin: Can manage facilities, leagues, scores, and users.'),
   ('STUDENT', 'Student: Can join leagues, create teams, and view schedules.'),
   ('ADMIN', ''),
   ('MANAGER', ''),
   ('EMPLOYEE', '');


INSERT IGNORE INTO permissions (name, description) VALUES
   ('MANAGE_OWN_PROFILE', 'Allows user to update their personal info and password.'),
   ('USE_MESSAGING', 'Allows user to send/receive messages with other participants.'),
   ('VIEW_LEAGUES', 'Allows user to browse and search available leagues and activities.'),
   ('REGISTER_FOR_LEAGUE', 'Allows user to register as an individual for a league.'),
   ('CREATE_TEAM', 'Allows user to create a new team as a captain.'),
   ('MANAGE_TEAM_INVITATIONS', 'Allows user to accept or decline invitations to a team.'),
   ('VIEW_OWN_SCHEDULE', 'Allows user to view their personal and team game schedule.'),
   ('VIEW_STANDINGS', 'Allows user to view league standings and team statistics.'),
   ('MANAGE_FACILITIES', 'Allows user to C/R/U/D locations, fields, and courts.'),
   ('MANAGE_SCHEDULES', 'Allows user to C/R/U/D leagues, activities, and games.'),
   ('MANAGE_REGISTRATIONS', 'Allows user to view and approve team registrations.'),
   ('MANAGE_SCORES', 'Allows user to enter and confirm game scores.'),
   ('SEND_ANNOUNCEMENTS', 'Allows user to send messages to individuals, teams, and leagues.'),
   ('MANAGE_ORDERS', 'Allows users to CRUD orders'),
   ('VIEW_REPORTS', '');

INSERT IGNORE INTO permission_role (role_id, permission_id) VALUES
      ((SELECT id FROM roles WHERE name = 'STUDENT'), (SELECT id FROM permissions WHERE name = 'MANAGE_OWN_PROFILE')),
      ((SELECT id FROM roles WHERE name = 'STUDENT'), (SELECT id FROM permissions WHERE name = 'USE_MESSAGING')),
      ((SELECT id FROM roles WHERE name = 'STUDENT'), (SELECT id FROM permissions WHERE name = 'VIEW_LEAGUES')),
      ((SELECT id FROM roles WHERE name = 'STUDENT'), (SELECT id FROM permissions WHERE name = 'REGISTER_FOR_LEAGUE')),
      ((SELECT id FROM roles WHERE name = 'STUDENT'), (SELECT id FROM permissions WHERE name = 'CREATE_TEAM')),
      ((SELECT id FROM roles WHERE name = 'STUDENT'), (SELECT id FROM permissions WHERE name = 'MANAGE_TEAM_INVITATIONS')),
      ((SELECT id FROM roles WHERE name = 'STUDENT'), (SELECT id FROM permissions WHERE name = 'VIEW_OWN_SCHEDULE')),
      ((SELECT id FROM roles WHERE name = 'STUDENT'), (SELECT id FROM permissions WHERE name = 'VIEW_STANDINGS'));

INSERT IGNORE INTO permission_role (role_id, permission_id) VALUES
    ((SELECT id FROM roles WHERE name = 'SCHOOL_ADMIN'), (SELECT id FROM permissions WHERE name = 'MANAGE_OWN_PROFILE')),
    ((SELECT id FROM roles WHERE name = 'SCHOOL_ADMIN'), (SELECT id FROM permissions WHERE name = 'USE_MESSAGING')),
    ((SELECT id FROM roles WHERE name = 'SCHOOL_ADMIN'), (SELECT id FROM permissions WHERE name = 'VIEW_LEAGUES')),
    ((SELECT id FROM roles WHERE name = 'SCHOOL_ADMIN'), (SELECT id FROM permissions WHERE name = 'REGISTER_FOR_LEAGUE')),
    ((SELECT id FROM roles WHERE name = 'SCHOOL_ADMIN'), (SELECT id FROM permissions WHERE name = 'CREATE_TEAM')),
    ((SELECT id FROM roles WHERE name = 'SCHOOL_ADMIN'), (SELECT id FROM permissions WHERE name = 'MANAGE_TEAM_INVITATIONS')),
    ((SELECT id FROM roles WHERE name = 'SCHOOL_ADMIN'), (SELECT id FROM permissions WHERE name = 'VIEW_OWN_SCHEDULE')),
    ((SELECT id FROM roles WHERE name = 'SCHOOL_ADMIN'), (SELECT id FROM permissions WHERE name = 'VIEW_STANDINGS')),
    ((SELECT id FROM roles WHERE name = 'SCHOOL_ADMIN'), (SELECT id FROM permissions WHERE name = 'MANAGE_FACILITIES')),
    ((SELECT id FROM roles WHERE name = 'SCHOOL_ADMIN'), (SELECT id FROM permissions WHERE name = 'MANAGE_SCHEDULES')),
    ((SELECT id FROM roles WHERE name = 'SCHOOL_ADMIN'), (SELECT id FROM permissions WHERE name = 'MANAGE_REGISTRATIONS')),
    ((SELECT id FROM roles WHERE name = 'SCHOOL_ADMIN'), (SELECT id FROM permissions WHERE name = 'MANAGE_SCORES')),
    ((SELECT id FROM roles WHERE name = 'SCHOOL_ADMIN'), (SELECT id FROM permissions WHERE name = 'SEND_ANNOUNCEMENTS')),
    ((SELECT id FROM roles WHERE name = 'ADMIN'), (SELECT id FROM permissions WHERE name = 'MANAGE_ORDERS')),
    ((SELECT id FROM roles WHERE name = 'ADMIN'), (SELECT id FROM permissions WHERE name = 'VIEW_REPORTS')),
    ((SELECT id FROM roles WHERE name = 'MANAGER'), (SELECT id FROM permissions WHERE name = 'MANAGE_ORDERS')),
    ((SELECT id FROM roles WHERE name = 'MANAGER'), (SELECT id FROM permissions WHERE name = 'VIEW_REPORTS')),
    ((SELECT id FROM roles WHERE name = 'EMPLOYEE'), (SELECT id FROM permissions WHERE name = 'MANAGE_ORDERS'));




INSERT IGNORE INTO users (first_name, last_name, email, password_hash) VALUES
     ('Brett', 'School Admin', 'brett.baumgart@kirkwood.edu', 'hashed_password_for_brett'),
     ('Alex', 'Student', 'alex.student@student.kirkwood.edu', 'hashed_password_for_alex'),
     ('John', 'Admin', 'admin@store.com', 'hash_admin'),
     ('Sara', 'Manager', 'manager@store.com', 'hash_manager'),
     ('Mike', 'Employee', 'employee@store.com', 'hash_employee');

INSERT IGNORE INTO user_roles (user_id, role_id) VALUES
     ((SELECT id FROM users WHERE email = 'brett.baumgart@kirkwood.edu'), (SELECT id FROM roles WHERE name = 'SCHOOL_ADMIN')),
     ((SELECT id FROM users WHERE email = 'alex.student@student.kirkwood.edu'), (SELECT id FROM roles WHERE name = 'STUDENT')),
     ((SELECT id FROM users WHERE email = 'admin@store.com'), (SELECT id FROM roles WHERE name = 'ADMIN')),
     ((SELECT id FROM users WHERE email = 'manager@store.com'), (SELECT id FROM roles WHERE name = 'MANAGER')),
     ((SELECT id FROM users WHERE email = 'employee@store.com'), (SELECT id FROM roles WHERE name = 'EMPLOYEE'));

INSERT IGNORE INTO departments (name, status)
VALUES
  ('Sales', 'ACTIVE'),
  ('Warehouse', 'ACTIVE'),
  ('Management', 'ACTIVE');

INSERT IGNORE INTO employees (departments_id, users_id, hire_date, status)
VALUES
  ((SELECT id FROM departments WHERE name = 'Sales'), (SELECT id FROM users WHERE email = 'admin@store.com'), '2023-01-10', 'ACTIVE'),
  ((SELECT id FROM departments WHERE name = 'Warehouse'), (SELECT id FROM users WHERE email = 'manager@store.com'), '2023-05-12', 'ACTIVE'),
  ((SELECT id FROM departments WHERE name = 'Management'), (SELECT id FROM users WHERE email = 'employee@store.com'), '2024-02-01', 'ACTIVE');



INSERT IGNORE INTO schools (name, domain, status_id) VALUES
   ('Kirkwood Community College', 'kirkwood.edu', 'ACTIVE'),
   ('University of Iowa', 'uiowa.edu', 'ACTIVE'),
   ('Iowa State University', 'iastate.edu', 'ACTIVE'),
   ('University of Northern Iowa', 'uni.edu', 'ACTIVE'),
   ('Coe College', 'coe.edu', 'ACTIVE'),
   ('Mount Mercy University', 'mtmercy.edu', 'ACTIVE'),
   ('Drake University', 'drake.edu', 'ACTIVE'),
   ('Grinnell College', 'grinnell.edu', 'ACTIVE'),
   ('Luther College', 'luther.edu', 'ACTIVE'),
   ('Simpson College', 'simpson.edu', 'ACTIVE'), -- Testing status
   ('Wartburg College', 'wartburg.edu', 'ACTIVE'),
   ('Cornell College', 'cornellcollege.edu', 'ACTIVE'),
   ('Loras College', 'loras.edu', 'ACTIVE'),
   ('Clarke University', 'clarke.edu', 'SUSPENDED'), -- Testing status
   ('St. Ambrose University', 'sau.edu', 'ACTIVE');

INSERT IGNORE INTO locations (school_id, name, description, address, status_id) VALUES
  (1, 'Main Campus', 'The primary campus in Cedar Rapids', '6301 Kirkwood Blvd SW, Cedar Rapids, IA', 'ACTIVE');

INSERT IGNORE INTO locations (school_id, name, description, address, status_id) VALUES
  (2, 'Carver-Hawkeye Arena', 'Main sports arena', '1 Elliott Dr, Iowa City, IA', 'ACTIVE');

INSERT IGNORE INTO locations (school_id, parent_location_id, name, description, status_id) VALUES
   (1, 1, 'Michael J Gould Rec Center', 'Student recreation facility', 'ACTIVE'),
   (1, 1, 'Johnson Hall', 'Athletics building and gymnasium', 'ACTIVE');

INSERT IGNORE INTO locations (school_id, parent_location_id, name, description, status_id) VALUES
   (2, 2, 'Main Court', 'The primary basketball court', 'ACTIVE'),
   (2, 2, 'Weight Room', 'Athlete training facility', 'COMING_SOON');

INSERT IGNORE INTO locations (school_id, parent_location_id, name, description, status_id) VALUES
   (1, 3, 'Basketball Court 1', 'North court', 'ACTIVE'),
   (1, 3, 'Basketball Court 2', 'South court', 'ACTIVE');


INSERT IGNORE INTO product_categories (id, name)
VALUES
  (1, 'Furniture & Home'),
  (2, 'Tools & Equipment');

INSERT IGNORE INTO products (id, name, domain, product_category_id, quantity, price)
VALUES
  (1, 'Chair', 'China', 1, 50, 30.99),
  (2, 'Table', 'India', 1, 44, 69.99),
  (3, 'Snow Machine', 'China', 2, 30, 499.99),
  (4, 'Rugs', 'Turkish', 1, 70, 120.45),
  (5, 'Painting Machine', 'China', 2, 25, 299.00),
  (6, 'Rugs Cleaner Machine', 'UK', 2, 40, 600.65),
  (7, 'Masterroom Bed', 'Italy', 1, 12, 690.99),
  (8, 'Doors', 'China', 1, 50, 120.00),
  (9, 'Marc''s Demo Product', 'Germany', 1, 11, 11.10);



INSERT IGNORE INTO suppliers (id, name, phone, country, city, state)
VALUES
  (1, 'China Industrial Co', '111-1111', 'China', 'Shanghai', 'SH'),
  (2, 'European Home Goods', '222-2222', 'Germany', 'Berlin', 'BE'),
  (3, 'UK Machinery Ltd', '333-3333', 'UK', 'London', 'LDN'),
  (4, 'Italian Furniture Group', '444-4444', 'Italy', 'Milan', 'MI');

INSERT IGNORE INTO product_suppliers (id, products_id, suppliers_id, quantity, cost_per_unit, supply_date)
VALUES
  (1, 1, 1, 100, 20.00, NOW()),
  (2, 2, 1, 80, 45.00, NOW()),
  (3, 3, 1, 40, 350.00, NOW()),
  (4, 4, 2, 60, 90.00, NOW()),
  (5, 5, 1, 30, 220.00, NOW()),
  (6, 6, 3, 35, 500.00, NOW()),
  (7, 7, 4, 20, 550.00, NOW()),
  (8, 8, 1, 70, 95.00, NOW()),
  (9, 9, 2, 15, 5.00, NOW());

INSERT IGNORE INTO orders (id, sale_date, users_id)
VALUES
  (1, NOW(), 2),
  (2, NOW(), 3);

INSERT IGNORE INTO order_items (id, orders_id, products_id, quantity, sale_price)
VALUES
  (1, 1, 1, 2, 30.99),
  (2, 1, 4, 1, 120.45),
  (3, 2, 3, 1, 499.99),
  (4, 2, 9, 3, 11.10);

INSERT IGNORE INTO subscriptions (name, description, monthly_price, annual_price) VALUES
('Free', 'Get started with 10 free leagues for your college or university.', 0, 0),
('Pro', 'Create up to 25 leagues for your college or university.', 25, 250);

