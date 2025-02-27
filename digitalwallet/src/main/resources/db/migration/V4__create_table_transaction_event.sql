CREATE TABLE transaction_event (
    id UUID PRIMARY KEY,
    payer_name VARCHAR(50) NOT NULL,
    payee_name VARCHAR(50) NOT NULL,
    payer_email VARCHAR(80) NOT NULL,
    payee_email VARCHAR(80) NOT NULL,
    amount DECIMAL(15, 2),
    "date" TIMESTAMPTZ NOT NULL,
    transaction_event_type VARCHAR(5) NOT NULL,
    sent BOOLEAN,
    processed BOOLEAN
);

CREATE INDEX idx_transaction_event_date ON transaction_event("date");
