-- Create customers table
CREATE TABLE customers (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL
);

-- Create accounts table
CREATE TABLE accounts (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          account_number VARCHAR(255) NOT NULL UNIQUE,
                          balance DOUBLE NOT NULL,
                          customer_id BIGINT NOT NULL,
                          FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Create transactions table
CREATE TABLE transactions (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              account_number VARCHAR(255) NOT NULL,
                              amount DOUBLE NOT NULL,
                              transaction_type VARCHAR(255) NOT NULL,
                              transaction_date TIMESTAMP NOT NULL,
                              FOREIGN KEY (account_number) REFERENCES accounts(account_number)
);
