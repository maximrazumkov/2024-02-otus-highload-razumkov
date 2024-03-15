CREATE TABLE usr
(
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR NOT NULL,
    second_name   VARCHAR NOT NULL,
    birthdate     DATE NOT NULL,
    gender CHAR(3) NOT NULL,
    biography     VARCHAR NOT NULL,
    city    VARCHAR NOT NULL,
    password  VARCHAR NOT NULL
);