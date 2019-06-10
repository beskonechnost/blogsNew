INSERT INTO users (ID, ENABLED, FIRST_NAME, LAST_NAME, username, password, role) VALUES (1, true , 'Andrew', 'Ko', 'admin', '$2a$10$gQv4NjC4QoN1SzQoG1602eUm.yBzsvzqxMkDg2kLlgrsxM4JX.5TS', 'PUBLISHER');
INSERT INTO users (ID, ENABLED, FIRST_NAME, LAST_NAME, username, password, role) VALUES (2, true , 'User', 'User', 'user', '$2a$10$umXMnGcZsVbWlOGA6ZYPSOZAtoYPvdbHU/bICiSUPdmpbQq9O99Yq', 'PUBLISHER');
INSERT INTO users (ID, ENABLED, FIRST_NAME, LAST_NAME, username, password, role) VALUES (2, true , 'UserTest', 'UserTest', 'userTest', '$2a$10$HvOSK1Z/ZXa9DlKADf.EYOy68xCMZolzWW8/P1KizgtKmVWdPm65m', 'PUBLISHER');

INSERT INTO messages (ID, create_date, message_text, modified_date, user_id) VALUES (1, '2019-06-10 09:49:31' , 'Hello!', '2019-06-10 09:49:31', 1);
INSERT INTO messages (ID, create_date, message_text, modified_date, user_id) VALUES (2, '2019-06-10 09:49:57' , 'How are you?', '2019-06-10 09:49:57', 1);
INSERT INTO messages (ID, create_date, message_text, modified_date, user_id) VALUES (3, '2019-06-10 09:51:12' , 'Hi! I am fine!!!', '2019-06-10 09:51:50', 2);
INSERT INTO messages (ID, create_date, message_text, modified_date, user_id) VALUES (4, '2019-06-10 09:53:42' , 'test test test test test test test test test test test test test test test test test test test test test test test test test test test test test!!!!', '2019-06-10 09:53:42', 1);