insert into users(username, password, enabled)
values('piyush.ramavat', 'Welcome@1', true),
('chintan.gatecha', 'Welcome@1', true),
('sample.user', 'Welcome@1', true),
('sample.admin', 'Welcome@1', true);


insert into authorities(username, authority)
values('piyush.ramavat', 'ROLE_SUPER_ADMIN'),
  ('piyush.ramavat', 'ROLE_ADMIN'),
  ('piyush.ramavat', 'ROLE_USER'),
  ('chintan.gatecha', 'ROLE_SUPER_ADMIN'),
  ('chintan.gatecha', 'ROLE_ADMIN'),
  ('chintan.gatecha', 'ROLE_USER'),
  ('sample.admin', 'ROLE_ADMIN'),
  ('sample.admin', 'ROLE_USER'),
  ('sample.user', 'ROLE_USER');