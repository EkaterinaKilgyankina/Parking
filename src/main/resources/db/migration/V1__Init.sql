CREATE TABLE "user"
(
    "id"       bigserial PRIMARY KEY,
    "email"    text NOT NULL,
    "password" text NOT NULL
);

CREATE TABLE "car"
(
    "id"         bigserial PRIMARY KEY,
    "user_id"    bigint NOT NULL,
    "number" text   NOT NULL
);

CREATE TABLE "spot"
(
    "id"   bigserial PRIMARY KEY,
    "location" text NOT NULL
);

CREATE TABLE "booking"
(
    "id"      bigserial PRIMARY KEY,
    "car_id"  bigint    not null,
    "spot_id" bigint    not null,
    "from"    timestamp NOT null,
    "to"      timestamp NOT NULL
);

alter TABLE "car"
    add constraint car_user_id_fkey foreign key (user_id) references "user" (id);
alter TABLE "booking"
    add constraint booking_car_id_fkey foreign key (car_id) references "car" (id);
alter TABLE "booking"
    add constraint booking_spot_id_fkey foreign key (spot_id) references "spot" (id);

INSERT INTO "spot" VALUES (1, 'A1');
INSERT INTO "spot" VALUES (2, 'A2');
INSERT INTO "spot" VALUES (3, 'A3');
INSERT INTO "spot" VALUES (4, 'A4');
INSERT INTO "spot" VALUES (5, 'A5');
INSERT INTO "spot" VALUES (6, 'A6');
INSERT INTO "spot" VALUES (7, 'A7');
INSERT INTO "spot" VALUES (8, 'A8');
INSERT INTO "spot" VALUES (9, 'A9');
INSERT INTO "spot" VALUES (10, 'A10');

INSERT INTO "user" VALUES (1, 'Alexander', '1234');
INSERT INTO "user" VALUES (2, 'Anton', '4567');

INSERT INTO "car" VALUES (1, 2, 'A456BC');
INSERT INTO "car" VALUES (2, 1, 'D789AE');

INSERT INTO "booking" VALUES (1, 2, 8, '2021-12-20 15:20:00', '2021-12-20 17:00:00');
INSERT INTO "booking" VALUES (2, 1, 4, '2021-12-21 16:40:00', '2021-12-22 19:20:00');
