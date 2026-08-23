\set ON_ERROR_STOP off

SELECT public_id, full_name, status FROM customer ORDER BY public_id;
SELECT a.account_number, c.public_id
FROM account a
JOIN customer c ON c.customer_id = a.customer_id;

BEGIN;

SAVEPOINT negative_test;

-- invalid status
INSERT INTO customer (public_id, full_name, email_normalized, status)
VALUES ('CUS-X', 'Bad Status', 'bad@example.com', 'UNKNOWN');
-- expect SQLSTATE/02290

ROLLBACK TO SAVEPOINT negative_test;
SAVEPOINT negative_test;

-- duplicate email
INSERT INTO customer (public_id, full_name, email_normalized, status)
VALUES ('CUS-DUPE', 'Dupe', 'amina@example.com', 'PROSPECT');
-- expect SQLSTATE/00001

ROLLBACK TO SAVEPOINT negative_test;
SAVEPOINT negative_test;

-- orphan account FK
INSERT INTO account (account_number, customer_id, account_type, balance)
VALUES ('ACCT-ORPHAN', 999999, 'CHECKING', 0);
-- expect SQLSTATE/02291

ROLLBACK TO SAVEPOINT negative_test;
COMMIT; -- no net change

\set ON_ERROR_STOP on