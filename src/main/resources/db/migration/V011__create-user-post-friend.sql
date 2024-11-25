INSERT INTO public.usr(id, first_name, second_name, birthdate, gender, biography, city, password) VALUES
('2ca6719b-f094-424d-aa73-8d7d96cdfd9a', 'Виктория', 'Абрамова', '1995-08-01', 'жен', 'Everythings', 'Москва', 'tIyg/EmjCZzMrOGa7Kj80KfScmQejZ05MmQMBerMzt9O+rH3m2R+8IC8n1Pc8C+VJaM6I3BFDIr4');

INSERT INTO public.usr(id, first_name, second_name, birthdate, gender, biography, city, password) VALUES
('c2625e9a-2051-4f02-9349-bbde7c0941f2', 'Роберт', 'Копчик', '2002-06-16', 'муж', 'Everythings', 'Санкт-Петербург', 'tIyg/EmjCZzMrOGa7Kj80KfScmQejZ05MmQMBerMzt9O+rH3m2R+8IC8n1Pc8C+VJaM6I3BFDIr4');

INSERT INTO public.usr(id, first_name, second_name, birthdate, gender, biography, city, password) VALUES
('6a149cbe-0dda-428a-bd1d-7dcb74ece5a9', 'Мартин', 'Семечков', '2001-01-11', 'муж', 'Everythings', 'Смоленск', 'tIyg/EmjCZzMrOGa7Kj80KfScmQejZ05MmQMBerMzt9O+rH3m2R+8IC8n1Pc8C+VJaM6I3BFDIr4');

INSERT INTO public.usr(id, first_name, second_name, birthdate, gender, biography, city, password) VALUES
('513f2231-87fb-4474-9035-1778150056e8', 'Кевин', 'ЭВанс', '1999-02-01', 'муж', 'Everythings', 'Воткинск', 'tIyg/EmjCZzMrOGa7Kj80KfScmQejZ05MmQMBerMzt9O+rH3m2R+8IC8n1Pc8C+VJaM6I3BFDIr4');

INSERT INTO post (usr_id, post, created_at) VALUES ('2ca6719b-f094-424d-aa73-8d7d96cdfd9a', 'Pellentesque habitant morbi tristique senectus. Quis commodo odio.', CURRENT_TIMESTAMP);

INSERT INTO post (usr_id, post, created_at) VALUES ('2ca6719b-f094-424d-aa73-8d7d96cdfd9a', 'Tempus egestas sed sed risus pretium quam vulputate dignissim suspendisse. Eget felis eget nunc.', CURRENT_TIMESTAMP);

INSERT INTO post (usr_id, post, created_at) VALUES ('2ca6719b-f094-424d-aa73-8d7d96cdfd9a', 'Amet facilisis magna etiam tempor orci.', CURRENT_TIMESTAMP);

INSERT INTO post (usr_id, post, created_at) VALUES ('2ca6719b-f094-424d-aa73-8d7d96cdfd9a', 'Sagittis purus sit amet volutpat consequat mauris nunc.', CURRENT_TIMESTAMP);

INSERT INTO post (usr_id, post, created_at) VALUES ('513f2231-87fb-4474-9035-1778150056e8', 'Convallis aenean et tortor at risus.', CURRENT_TIMESTAMP);

INSERT INTO post (usr_id, post, created_at) VALUES ('513f2231-87fb-4474-9035-1778150056e8', 'Mattis ullamcorper velit sed ullamcorper morbi tincidunt.', CURRENT_TIMESTAMP);

INSERT INTO post (usr_id, post, created_at) VALUES ('6a149cbe-0dda-428a-bd1d-7dcb74ece5a9', 'Est placerat in egestas erat imperdiet sed euismod.', CURRENT_TIMESTAMP);

INSERT INTO friend (usr_id, friend_id) VALUES ('c2625e9a-2051-4f02-9349-bbde7c0941f2', '2ca6719b-f094-424d-aa73-8d7d96cdfd9a');

INSERT INTO friend (usr_id, friend_id) VALUES ('6a149cbe-0dda-428a-bd1d-7dcb74ece5a9', '2ca6719b-f094-424d-aa73-8d7d96cdfd9a');

INSERT INTO friend (usr_id, friend_id) VALUES ('513f2231-87fb-4474-9035-1778150056e8', '2ca6719b-f094-424d-aa73-8d7d96cdfd9a');

INSERT INTO friend (usr_id, friend_id) VALUES ('513f2231-87fb-4474-9035-1778150056e8', '6a149cbe-0dda-428a-bd1d-7dcb74ece5a9');

INSERT INTO friend (usr_id, friend_id) VALUES ('6a149cbe-0dda-428a-bd1d-7dcb74ece5a9', '513f2231-87fb-4474-9035-1778150056e8');

