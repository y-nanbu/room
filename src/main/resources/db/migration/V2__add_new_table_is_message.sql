CREATE TABLE "message"
(
    "id"            SERIAL PRIMARY KEY NOT NULL,
    "message"       VARCHAR(2000),
    "user_id"       VARCHAR(16) NOT NULL,
    "updated_at"    TIMESTAMP NOT NULL,
    "created_at"    TIMESTAMP NOT NULL,
    "last_updated"  VARCHAR(16) NOT NULL
);