create table "transaction"(
    id uuid primary key,
    payer_wallet_id uuid not null,
    payee_wallet_id uuid not null,
    amount decimal(15, 2) not null,
    "date" timestamptz not null,
    foreign key(payer_wallet_id) references wallet(id),
    foreign key(payee_wallet_id) references wallet(id)
);