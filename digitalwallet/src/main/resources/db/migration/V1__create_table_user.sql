create table "user"(
    id uuid primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    type varchar(15) not null,
    document varchar(14) not null unique,
    email varchar(80) not null unique,
    password varchar(80) not null
);

insert into "user" values (
    '3a434452-3f8f-4c9b-9897-23b1e264bbc2',
    'Jean Carlos',
    'Fernandes Mendes',
    'CUSTOMER',
    '437.221.220-82',
    'jean@email.com',
    '$2a$12$RofHs5HRw5LFQq5Ya.ttruvYMX8nNtlZvc0fOvdzut6UAOx7XNHDG'
);

insert into "user" values (
    '1a0daf85-86d4-43b7-afe3-64273f1b0f9d',
    'Ana Caroline',
    'dos Santos Paiva',
    'CUSTOMER',
    '502.764.850-25',
    'carol@email.com',
    '$2a$12$RofHs5HRw5LFQq5Ya.ttruvYMX8nNtlZvc0fOvdzut6UAOx7XNHDG'
);