INSERT INTO user(user_id, user_name, user_surname, user_email, user_login, user_password, user_birthday) VALUES (1, 'Carmelo', 'Anthony', 'cant@gmail.com', 'user', 'user', '1979-05-15');
INSERT INTO user(user_id, user_name, user_surname, user_email, user_login, user_password, user_birthday) VALUES (2, 'Paul', 'George', 'george@gmail.com', 'admin', 'admin', '1999-07-25');
INSERT INTO role(role_id, role_name) VALUES (1, 'USER');
INSERT INTO role(role_id, role_name) VALUES (2, 'ADMIN');
INSERT INTO userrole(user_role_id, id_user, id_role) VALUES (1, 1, 1);
INSERT INTO userrole(user_role_id, id_user, id_role) VALUES (2, 2, 1);
INSERT INTO userrole(user_role_id, id_user, id_role) VALUES (3, 2, 2);
INSERT INTO author(author_id, author_name, author_surname) VALUES (1, 'Craig', 'Walls');
INSERT INTO author(author_id, author_name, author_surname) VALUES (2, 'Joseph', 'B. Ottinger');
INSERT INTO author(author_id, author_name, author_surname) VALUES (3, 'Andrew', 'Lombardi');
INSERT INTO book(book_id, book_title, book_description, book_units_in_stock) VALUES (1, 'Spring In Action', 'Fourth Edition', 4);
INSERT INTO book(book_id, book_title, book_description, book_units_in_stock) VALUES (2, 'Beginning Spring 5', 'From Novice to Professional', 1);
INSERT INTO authorbook(author_book_id, id_author, id_book) VALUES (1, 1, 1);
INSERT INTO authorbook(author_book_id, id_author, id_book) VALUES (2, 2, 2);
INSERT INTO authorbook(author_book_id, id_author, id_book) VALUES (3, 3, 2);