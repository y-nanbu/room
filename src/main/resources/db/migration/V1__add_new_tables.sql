create TABLE "USERS" (
    "ID" VARCHAR(16) not null,
    "NAME" VARCHAR(50) not null,
    "PASSWORD" VARCHAR(60) not null,
    "UPDATED_AT" TIMESTAMP not null,
    "CREATED_AT" TIMESTAMP not null,
    "LAST_UPDATED" VARCHAR(16) not null default 'unknown',
    PRIMARY KEY ("ID")
);

create TABLE "MESSAGE" (
    "ID" BIGINT not null IDENTITY,
    "MESSAGE" VARCHAR(5000),
    "USER_NAME" VARCHAR(50) not null,
    "UPDATED_AT" TIMESTAMP not null,
    "CREATED_AT" TIMESTAMP not null,
    "LAST_UPDATED" VARCHAR(16) not null default 'unknown',
    PRIMARY KEY ("ID")
);