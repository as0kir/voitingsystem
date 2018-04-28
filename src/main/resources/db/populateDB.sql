DELETE FROM voites;
DELETE FROM dishes;
DELETE FROM menu;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),     -- 100000
  ('Admin', 'admin@gmail.com', 'admin');      -- 100001

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO restaurants(name) VALUES
  ('1Утюг'),                                  -- 100002
  ('2Шашлык');                                -- 100003

INSERT INTO menu(id_restaurant, date_set) VALUES
  (100002, '2018-04-07 00:00:00'),            -- 100004
  (100002, '2018-04-08 00:00:00'),            -- 100005
  (100003, '2018-04-07 00:00:00'),            -- 100006
  (100003, '2018-04-08 00:00:00');            -- 100007


INSERT INTO dishes(id_menu, name, price) VALUES
  (100004, '1Борщ', 50),                       -- 100008
  (100004, '2Котлета', 30),                    -- 100009
  (100004, '3Компот', 20.50),                  -- 100010
  (100005, '1Оливье', 72.30);                  -- 100011

INSERT INTO voites(id_user, date_set, id_menu) VALUES
  (100000, '2018-04-07 09:30:00', 100004),     -- 100012
  (100000, '2018-04-08 10:20:00', 100005),     -- 100013
  (100001, '2018-04-07 08:10:00', 100004);     -- 100013

