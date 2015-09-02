CREATE TABLE "users" (
    "id" varchar(16) not null,
    "name" varchar(50) not null,
    "password" varchar(16) not null,
    "updated_at" timestamp(3)  not null,
    "created_at" timestamp(3) not null,
    "last_updated" varchar(16) not null default 'unknown'::character varying,
    CONSTRAINT users_id_pk PRIMARY KEY ("id")
);