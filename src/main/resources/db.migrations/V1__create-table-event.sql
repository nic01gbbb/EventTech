CREATE EXTENSION IF NOT EXISTS "pgcrypto";


CREATE TABLE event
(
    id          UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title       VARCHAR(100) NOT NULL,
    description VARCHAR(250) NOT NULL,
    imgurl      VARCHAR(2000) NOT NULL,
    eventurl    VARCHAR(100) NOT NULL,
    date        TIMESTAMP    NOT NULL,
    remote      BOOLEAN      NOT NULL
)