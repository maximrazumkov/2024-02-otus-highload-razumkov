CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Таблица friend
CREATE TABLE friend (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    usr_id UUID NOT NULL,
    friend_id UUID NOT NULL,
    CONSTRAINT fk_usr FOREIGN KEY (usr_id) REFERENCES usr(id),
    CONSTRAINT fk_friend FOREIGN KEY (friend_id) REFERENCES usr(id)
);

-- Таблица post
CREATE TABLE post (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    usr_id UUID NOT NULL,
    post VARCHAR,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_post_usr FOREIGN KEY (usr_id) REFERENCES usr(id)
);