create table wallet(
    id uuid primary key,
    user_id uuid not null unique,
    balance decimal(15, 2) not null,
    foreign key(user_id) references "user"(id)
);

insert into wallet (id, user_id, balance)
values('7db4800c-9375-43d7-a4c5-d89250c8ae39', '3a434452-3f8f-4c9b-9897-23b1e264bbc2', 200.00);

insert into wallet (id, user_id, balance)
values('f2a41253-93f9-4f2e-bfd3-03933020ad2f', '1a0daf85-86d4-43b7-afe3-64273f1b0f9d', 500.00);