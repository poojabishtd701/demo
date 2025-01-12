-- Insert sample data into customers table
INSERT INTO customers (id, name) VALUES (1, 'John Doe');
INSERT INTO customers (id, name) VALUES (2, 'Jane Smith');
INSERT INTO customers (id, name) VALUES (3, 'Alice Johnson');

-- Insert sample data into accounts table
INSERT INTO accounts (account_number, balance, customer_id) VALUES ('ACC12345', 1500.00, 1);
INSERT INTO accounts (account_number, balance, customer_id) VALUES ('ACC67890', 2200.50, 2);
INSERT INTO accounts (account_number, balance, customer_id) VALUES ('ACC11223', 850.75, 3);

-- Insert sample data into transactions table
INSERT INTO transactions (account_number, amount, transaction_type, transaction_date)
VALUES ('ACC12345', 500.00, 'Debit', '2025-01-08T12:30:00');

INSERT INTO transactions (account_number, amount, transaction_type, transaction_date)
VALUES ('ACC67890', 300.00, 'Credit', '2025-01-08T13:00:00');

INSERT INTO transactions (account_number, amount, transaction_type, transaction_date)
VALUES ('ACC12345', 200.00, 'Credit', '2025-01-08T14:00:00');

INSERT INTO transactions (account_number, amount, transaction_type, transaction_date)
VALUES ('ACC11223', 100.00, 'Debit', '2025-01-08T15:00:00');

INSERT INTO transactions (account_number, amount, transaction_type, transaction_date)
VALUES ('ACC67890', 150.00, 'Debit', '2025-01-08T16:00:00');

INSERT INTO transactions (account_number, amount, transaction_type, transaction_date)
VALUES ('ACC11223', 50.00, 'Credit', '2025-01-08T17:00:00');