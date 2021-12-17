CREATE TABLE "user"
(
    "id"       bigserial PRIMARY KEY,
    "email"    text NOT NULL,
    "password" text NOT NULL
);

CREATE TABLE "role"
(
    "id"   bigserial PRIMARY KEY,
    "name" text NOT NULL
);

CREATE TABLE "user_roles"
(
    "user_id" bigint NOT NULL,
    "role_id" bigint NOT NULL,
    UNIQUE (USER_ID, ROLE_ID)
);


CREATE TABLE "car"
(
    "id"             bigserial PRIMARY KEY,
    "user_id"        bigint NOT NULL,
    "number"         text   NOT NULL,
    "current_status" text   NOT NULL,
    UNIQUE (number)

);

CREATE TABLE "spot"
(
    "id"       bigserial PRIMARY KEY,
    "location" text NOT NULL,
    UNIQUE (location)

);

CREATE TABLE "booking"
(
    "id"           bigserial PRIMARY KEY,
    "car_id"       bigint    not null,
    "spot_id"      bigint    not null,
    "booking_from" timestamp NOT null,
    "booking_to"   timestamp NOT NULL
);


alter TABLE "user_roles"
    add constraint USER_ROLES_USER_ID_FKEY foreign key (user_id) references "user" (id);
alter TABLE "user_roles"
    add constraint USER_ROLES_ROLE_ID_FKEY foreign key (role_id) references "role" (id);

alter TABLE "car"
    add constraint car_user_id_fkey foreign key (user_id) references "user" (id);
alter TABLE "booking"
    add constraint booking_car_id_fkey foreign key (car_id) references "car" (id);
alter TABLE "booking"
    add constraint booking_spot_id_fkey foreign key (spot_id) references "spot" (id);

INSERT INTO "role"
VALUES (1, 'role_user');
INSERT INTO "role"
VALUES (2, 'role_admin');