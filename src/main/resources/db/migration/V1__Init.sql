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
    "number"     text   NOT NULL
);

CREATE TABLE "spot"
(
    "id"      bigserial PRIMARY KEY,
    "booking_id" bigint
);

CREATE TABLE "booking"
(
    "id"      bigserial PRIMARY KEY,
    "car_id"  bigint    not null,
    "spot_id" bigint    not null,
    "booking_from"    timestamp NOT null,
    "booking_to"      timestamp NOT NULL
);

alter TABLE "car"
    add constraint car_user_id_fkey foreign key (user_id) references "user" (id);
alter TABLE "spot"
    add constraint spot_booking_id_fkey foreign key (booking_id) references "booking" (id);
alter TABLE "booking"
    add constraint booking_car_id_fkey foreign key (car_id) references "car" (id);
alter TABLE "booking"
    add constraint booking_spot_id_fkey foreign key (spot_id) references "spot" (id);

